package bkh.com.silverspell.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import bkh.com.silverspell.Constants;
import bkh.com.silverspell.R;
import bkh.com.silverspell.fragment.ChangeLanguage;
import bkh.com.silverspell.fragment.MainFragment;
import bkh.com.silverspell.views.CustomConfirmDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.detail_container)
    FrameLayout detail_fragment_container;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private Fragment fragment = null;
    private AppCompatActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = Dashboard.this;
        ButterKnife.bind(mContext);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mContext, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment = new MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.detail_container, fragment);
        ft.addToBackStack(Constants.MAINFRAGMENT.name()).commit();

    }

    private int getFragmentCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentCount() > 1) {
                getSupportFragmentManager().popBackStackImmediate();
            } else if (getFragmentCount() == 1) {
                openExitDialog();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                fragment = new MainFragment();
                break;

            case R.id.nav_gallery:
                fragment = new ChangeLanguage();
                break;
            case R.id.nav_slideshow:
                break;

            default:
                fragment = new MainFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.detail_container, fragment);
            ft.addToBackStack("abc").commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openExitDialog() {
        CustomConfirmDialog.getInstance().showConfirmDialog(mContext, getString(R.string.exit_app), getString(R.string.ok), getString(R.string.cancel), new CustomConfirmDialog.OnClickInterFace() {
            @Override
            public void onOkay() {
                finishAffinity();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

            @Override
            public void onCancel() {
            }
        });
    }

}
