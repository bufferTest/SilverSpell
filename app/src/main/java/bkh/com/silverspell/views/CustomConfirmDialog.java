package bkh.com.silverspell.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import bkh.com.silverspell.R;

public class CustomConfirmDialog {
    private static final CustomConfirmDialog ourInstance = new CustomConfirmDialog();

    private CustomConfirmDialog() {
    }

    public static CustomConfirmDialog getInstance() {
        return ourInstance;
    }

    public void showConfirmDialog(final Context mContext, String message, String okbutton, String cancelButton, final OnClickInterFace onClickInterFace) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_confirm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.animationName;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
        TextView tvMessage = dialog.findViewById(R.id.tv_message);
        TextView btnCancel = dialog.findViewById(R.id.btn_cancel);
        TextView btnok = dialog.findViewById(R.id.btn_ok);
        tvMessage.setText(message);
        btnok.setText(okbutton);
        btnCancel.setText(cancelButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterFace.onCancel();
                dialog.dismiss();
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterFace.onOkay();
                dialog.dismiss();
            }
        });
    }

    public interface OnClickInterFace {
        void onOkay();

        void onCancel();
    }
}
