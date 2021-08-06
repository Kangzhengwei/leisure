package com.kzw.leisure.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.kzw.leisure.R;
import com.kzw.leisure.widgets.ToastUtil;


/**
 * Created by Android on 2019/2/28.
 */

public class AddWebSiteDialog extends Dialog {

    private clickListener clickListener;

    public AddWebSiteDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_web_site_dialog);
        TextInputEditText siteEdit = findViewById(R.id.site_edit);
        TextInputEditText urlEdit = findViewById(R.id.url_edit);
        TextView confirm = findViewById(R.id.confirm);
        TextView cancle = findViewById(R.id.cancle);
        cancle.setOnClickListener(v -> dismiss());
        confirm.setOnClickListener(v -> {
            dismiss();
            if (!TextUtils.isEmpty(siteEdit.getText().toString()) && !TextUtils.isEmpty(urlEdit.getText().toString())) {
                if (clickListener != null) {
                    clickListener.click(siteEdit.getText().toString(), urlEdit.getText().toString());
                }
            } else {
                ToastUtil.showToast("编辑不能为空");
            }
        });
    }

    public interface clickListener {
        void click(String site, String url);
    }

    public void setClickListener(clickListener listener) {
        clickListener = listener;
    }
}
