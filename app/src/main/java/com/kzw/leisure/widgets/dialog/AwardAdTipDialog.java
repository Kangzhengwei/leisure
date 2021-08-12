package com.kzw.leisure.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kzw.leisure.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AwardAdTipDialog extends Dialog {


    @BindView(R.id.message)
    TextView tvMessage;
    @BindView(R.id.negtive)
    Button negtive;
    @BindView(R.id.positive)
    Button positive;

    private positiveClickListener listener;
    private Unbinder bind;
    private String message;

    public static AwardAdTipDialog builder(Context context) {
        return new AwardAdTipDialog(context);
    }

    public AwardAdTipDialog(@NonNull Context context) {
        super(context, R.style.custom_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.award_tip_dialog);
        bind = ButterKnife.bind(this);
        initView();
    }


    @OnClick({R.id.negtive, R.id.positive})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.negtive:
                dismiss();
                break;
            case R.id.positive:
                if (listener != null) {
                    listener.positiveClick();
                }
                dismiss();
                break;
        }
    }
    private void initView(){
        tvMessage.setText(message);
    }

    @Override
    public void dismiss() {
        bind.unbind();
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
        initView();
    }

    public AwardAdTipDialog setMessage(String message) {
        this.message=message;
        return this;
    }

    public interface positiveClickListener {
        void positiveClick();
    }

    public AwardAdTipDialog setPositiveClickListener(positiveClickListener listener) {
        this.listener = listener;
        return this;
    }
}
