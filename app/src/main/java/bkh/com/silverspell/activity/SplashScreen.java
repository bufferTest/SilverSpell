package bkh.com.silverspell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.facebook.shimmer.ShimmerFrameLayout;

import bkh.com.silverspell.R;
import bkh.com.silverspell.utils.AppConstants;
import bkh.com.silverspell.utils.AppPreference;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout container;
    private AppCompatActivity mContext;
    private Handler handler;
    private Runnable runnable;
    private Animation animFadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mContext = SplashScreen.this;
        ButterKnife.bind(mContext);

        animFadein = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
        container.startAnimation(animFadein);

        container.setDuration(1200);
        container.startShimmerAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSplashTime();
    }

    private void setSplashTime() {

        runnable = new Runnable() {
            @Override
            public void run() {
                String userid = AppPreference.getInstance().getString(AppConstants.USERID);
                Intent intent = null;
                if (userid.trim().length() == 0) {
                    intent = new Intent(mContext, SignInActivity.class);

                } else {
                    intent = new Intent(mContext, Dashboard.class);
                }
                intent = new Intent(mContext, Dashboard.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();

            }
        };

        handler = new Handler();
        int SPLASH_TIME_DURATION = 2500;
        handler.postDelayed(runnable, SPLASH_TIME_DURATION);

    }

    @Override
    protected void onPause() {
        super.onPause();
        container.stopShimmerAnimation();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        container.stopShimmerAnimation();
        handler.removeCallbacks(runnable);
    }
}
