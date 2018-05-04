package bkh.com.silverspell.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bkh.com.silverspell.R;
import bkh.com.silverspell.models.Languages;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeLanguageAdapter extends RecyclerView.Adapter<ChangeLanguageAdapter.MyViewHolder> {

    @BindView(R.id.tv_language)
    TextView tvLanguage;
    private Context mContext;
    private List<Languages> languagesList;
    private onClickItem onClickItem;

    public ChangeLanguageAdapter(Context mContext, List<Languages> languagesList, onClickItem onClickItem) {
        this.mContext = mContext;
        this.languagesList = languagesList;
        this.onClickItem = onClickItem;
    }

    public interface onClickItem {
        public void onClick(String value);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvLanguage.setText(languagesList.get(position).getDisplay_name());
    }

    @Override
    public int getItemCount() {
        return languagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_language)
        TextView tvLanguage;
        @BindView(R.id.lay_expiry_container)
        ConstraintLayout layExpiryContainer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.tv_language)
        public void onClick() {
            onClickItem.onClick(languagesList.get(getLayoutPosition()).getLanguage());
        }
    }
}
