package bkh.com.silverspell.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bkh.com.silverspell.R;
import bkh.com.silverspell.adapter.ChangeLanguageAdapter;
import bkh.com.silverspell.models.Languages;
import bkh.com.silverspell.utils.AppPref;
import bkh.com.silverspell.utils.CommonUtils;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ChangeLanguage extends Fragment {

    @BindView(R.id.rv_languages)
    RecyclerView rvLanguages;

    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View getView = inflater.inflate(R.layout.fragment_change_language, container, false);

        ButterKnife.bind(this, getView);

        return getView;
    }

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Change Language");
    }

    @Override
    public void onResume() {
        super.onResume();

        setAdapter();
    }

    private void setAdapter() {
        List<Languages> languagesList= new ArrayList<>();
        Languages languages = new Languages("en","World","English");
        languagesList.add(languages);
        languages = new Languages("hi","India","Hindi");
        languagesList.add(languages);
        languages = new Languages("pa","India","Punjabi");
        languagesList.add(languages);

        ChangeLanguageAdapter adapter = new ChangeLanguageAdapter(mContext, languagesList, new ChangeLanguageAdapter.onClickItem() {
            @Override
            public void onClick(String value) {
                AppPref appPref = new AppPref(mContext);
                appPref.putString("APP_LANGUAGE",value);
                CommonUtils.getInstance().setLocale(mContext);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        rvLanguages.setHasFixedSize(true);
        rvLanguages.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvLanguages.setLayoutManager(llm);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
