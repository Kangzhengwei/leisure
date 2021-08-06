package com.kzw.leisure.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.kzw.leisure.R;
import com.kzw.leisure.utils.theme.ATH;
import com.kzw.leisure.widgets.pageView.ReadBookControl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: kang4
 * Date: 2019/12/13
 * Description:
 */
public class SettingMenu extends FrameLayout {
    @BindView(R.id.vw_bg)
    View vwBg;
    @BindView(R.id.tv_screen_direction)
    TextView tvScreenDirection;
    @BindView(R.id.ll_screen_direction)
    LinearLayout llScreenDirection;
    @BindView(R.id.tv_screen_time_out)
    TextView tvScreenTimeOut;
    @BindView(R.id.llScreenTimeOut)
    LinearLayout llScreenTimeOut;
    @BindView(R.id.tvJFConvert)
    TextView tvJFConvert;
    @BindView(R.id.llJFConvert)
    LinearLayout llJFConvert;

    private Context context;
    private ReadBookControl readBookControl = ReadBookControl.getInstance();
    private Callback callback;

    public SettingMenu(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public SettingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    public SettingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.setting_menu_layout, this);
        ButterKnife.bind(this, view);
        initData();
    }

    public void setListener(@NonNull Callback callback) {
        this.callback = callback;
    }
    private void initData() {
        upScreenDirection(readBookControl.getScreenDirection());
        upScreenTimeOut(readBookControl.getScreenTimeOut());
        upFConvert(readBookControl.getTextConvert());
    }


    private void upScreenDirection(int screenDirection) {
        String[] screenDirectionListTitle = context.getResources().getStringArray(R.array.screen_direction_list_title);
        if (screenDirection >= screenDirectionListTitle.length) {
            tvScreenDirection.setText(screenDirectionListTitle[0]);
        } else {
            tvScreenDirection.setText(screenDirectionListTitle[screenDirection]);
        }
    }

    private void upScreenTimeOut(int screenTimeOut) {
        tvScreenTimeOut.setText(context.getResources().getStringArray(R.array.screen_time_out)[screenTimeOut]);
    }

    private void upFConvert(int fConvert) {
        tvJFConvert.setText(context.getResources().getStringArray(R.array.convert_s)[fConvert]);
    }


    @OnClick({R.id.ll_screen_direction, R.id.llScreenTimeOut, R.id.llJFConvert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_screen_direction:
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.screen_direction))
                        .setSingleChoiceItems(context.getResources().getStringArray(R.array.screen_direction_list_title), readBookControl.getScreenDirection(), (dialogInterface, i) -> {
                            readBookControl.setScreenDirection(i);
                            upScreenDirection(i);
                            dialogInterface.dismiss();
                            callback.recreate();
                        })
                        .create();
                dialog.show();
                ATH.setAlertDialogTint(dialog);
                break;
            case R.id.llScreenTimeOut:
                AlertDialog screenDialog = new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.keep_light))
                        .setSingleChoiceItems(context.getResources().getStringArray(R.array.screen_time_out), readBookControl.getScreenTimeOut(), (dialogInterface, i) -> {
                            readBookControl.setScreenTimeOut(i);
                            upScreenTimeOut(i);
                            callback.keepScreenOnChange(i);
                            dialogInterface.dismiss();
                        })
                        .create();
                screenDialog.show();
                ATH.setAlertDialogTint(screenDialog);
                break;
            case R.id.llJFConvert:
                AlertDialog simToTranDialog = new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.jf_convert))
                        .setSingleChoiceItems(context.getResources().getStringArray(R.array.convert_s), readBookControl.getTextConvert(), (dialogInterface, i) -> {
                            readBookControl.setTextConvert(i);
                            upFConvert(i);
                            dialogInterface.dismiss();
                            callback.refreshPage();
                        })
                        .create();
                simToTranDialog.show();
                ATH.setAlertDialogTint(simToTranDialog);
                break;
        }
    }


    public interface Callback {
        void upBar();

        void keepScreenOnChange(int keepScreenOn);

        void recreate();

        void refreshPage();
    }
}
