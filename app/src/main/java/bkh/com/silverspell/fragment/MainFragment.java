package bkh.com.silverspell.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bkh.com.silverspell.R;
import bkh.com.silverspell.rate;
import bkh.com.silverspell.utils.AppConstants;
import bkh.com.silverspell.utils.AppPreference;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.btn_done)
    Button btnDone;
    @BindView(R.id.et_gold_price)
    EditText etGoldPrice;
    @BindView(R.id.et_silver_price)
    EditText etSilverPrice;

    private FirebaseDatabase mFirebaseInstance;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Prices");

        userid = AppPreference.getInstance().getString(AppConstants.USERID);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("silver spell");

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getData() {

        mFirebaseInstance.getReference("silver_rate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String rate = dataSnapshot.getValue(String.class);
                etSilverPrice.setText(rate);
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
        mFirebaseInstance.getReference("gold_rate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String rate = dataSnapshot.getValue(String.class);
                etGoldPrice.setText(rate);
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }

    @OnClick(R.id.btn_done)
    public void onViewClicked() {
        mFirebaseInstance.getReference("gold_rate").setValue(etGoldPrice.getText().toString());
        mFirebaseInstance.getReference("silver_rate").setValue(etSilverPrice.getText().toString());
    }
}
