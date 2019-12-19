package com.kzw.leisure.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kzw.leisure.R;
import com.kzw.leisure.utils.PermessionUtil;
import com.kzw.leisure.utils.theme.ATH;
import com.kzw.leisure.widgets.anim.PageAnimation;
import com.kzw.leisure.widgets.font.FontSelector;
import com.kzw.leisure.widgets.pageView.ReadBookControl;
import com.makeramen.roundedimageview.RoundedImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: kang4
 * Date: 2019/12/13
 * Description:
 */
public class UISettingMenu extends FrameLayout {
    @BindView(R.id.vw_bg)
    View vwBg;
    @BindView(R.id.nbTextSizeDec)
    ATEStrokeTextView nbTextSizeDec;
    @BindView(R.id.nbTextSize)
    TextView nbTextSize;
    @BindView(R.id.nbTextSizeAdd)
    ATEStrokeTextView nbTextSizeAdd;
    @BindView(R.id.fl_text_Bold)
    ATEStrokeTextView flTextBold;
    @BindView(R.id.fl_text_font)
    ATEStrokeTextView flTextFont;
    @BindView(R.id.fl_indent)
    ATEStrokeTextView flIndent;
    @BindView(R.id.tvPageMode)
    ATEStrokeTextView tvPageMode;
    @BindView(R.id.tvRowDef0)
    ATEStrokeTextView tvRowDef0;
    @BindView(R.id.tvRowDef1)
    ATEStrokeTextView tvRowDef1;
    @BindView(R.id.tvRowDef2)
    ATEStrokeTextView tvRowDef2;
    @BindView(R.id.tvRowDef)
    ATEStrokeTextView tvRowDef;
    @BindView(R.id.tvOther)
    RoundedImageView tvOther;
    @BindView(R.id.civ_bg_white)
    RoundedImageView civBgWhite;
    @BindView(R.id.tv0)
    TextView tv0;
    @BindView(R.id.civ_bg_yellow)
    RoundedImageView civBgYellow;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.civ_bg_green)
    RoundedImageView civBgGreen;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.civ_bg_blue)
    RoundedImageView civBgBlue;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.civ_bg_black)
    RoundedImageView civBgBlack;
    @BindView(R.id.tv4)
    TextView tv4;

    Activity activity;
    private ReadBookControl readBookControl = ReadBookControl.getInstance();
    private Callback callback;

    public UISettingMenu(Context context) {
        super(context);
        init(context);
    }

    public UISettingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UISettingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_setting_menu_layout, this);
        ButterKnife.bind(this, view);
    }

    public void setListener(Activity activity, @NonNull Callback callback) {
        this.activity = activity;
        this.callback = callback;
        initData();
    }

    private void initData() {
        setBg();
        updateBg(readBookControl.getTextDrawableIndex());
        updateBoldText(readBookControl.getTextBold());
        updatePageMode(readBookControl.getPageMode());
        nbTextSize.setText(String.format("%d", readBookControl.getTextSize()));
    }

    @OnClick({R.id.nbTextSizeDec, R.id.nbTextSizeAdd, R.id.fl_text_Bold, R.id.fl_text_font, R.id.fl_indent, R.id.tvPageMode, R.id.tvRowDef0, R.id.tvRowDef1, R.id.tvRowDef2, R.id.tvRowDef, R.id.tvOther, R.id.civ_bg_white, R.id.civ_bg_yellow, R.id.civ_bg_green, R.id.civ_bg_blue, R.id.civ_bg_black})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nbTextSizeDec:
                int decSize = readBookControl.getTextSize() - 1;
                if (decSize < 10) decSize = 10;
                readBookControl.setTextSize(decSize);
                nbTextSize.setText(String.format("%d", readBookControl.getTextSize()));
                callback.upTextSize();
                break;
            case R.id.nbTextSizeAdd:
                int addSize = readBookControl.getTextSize() + 1;
                if (addSize > 40) addSize = 40;
                readBookControl.setTextSize(addSize);
                nbTextSize.setText(String.format("%d", readBookControl.getTextSize()));
                callback.upTextSize();
                break;
            case R.id.fl_text_Bold:
                readBookControl.setTextBold(!readBookControl.getTextBold());
                updateBoldText(readBookControl.getTextBold());
                callback.upTextSize();
                break;
            case R.id.fl_text_font:
                if (PermessionUtil.checkStoragePermission(activity)) {
                    new FontSelector(activity, readBookControl.getFontPath())
                            .setListener(new FontSelector.OnThisListener() {
                                @Override
                                public void setDefault() {
                                    clearFontPath();
                                }

                                @Override
                                public void setFontPath(String fontPath) {
                                    setReadFonts(fontPath);
                                }
                            })
                            .create()
                            .show();
                } else {
                    ToastUtil.showSingleToast("需要文件访问权限");
                }
                break;
            case R.id.fl_indent:
                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.indent))
                        .setSingleChoiceItems(activity.getResources().getStringArray(R.array.indent),
                                readBookControl.getIndent(),
                                (dialogInterface, i) -> {
                                    readBookControl.setIndent(i);
                                    callback.refresh();
                                    dialogInterface.dismiss();
                                })
                        .create();
                dialog.show();
                ATH.setAlertDialogTint(dialog);
                break;
            case R.id.tvPageMode:
                AlertDialog modeDialog = new AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.page_mode))
                        .setSingleChoiceItems(PageAnimation.Mode.getAllPageMode(), readBookControl.getPageMode(), (dialogInterface, i) -> {
                            readBookControl.setPageMode(i);
                            updatePageMode(i);
                            callback.upPageMode();
                            dialogInterface.dismiss();
                        })
                        .create();
                modeDialog.show();
                ATH.setAlertDialogTint(modeDialog);
                break;
            case R.id.tvRowDef0:
                readBookControl.setLineMultiplier(0.6f);
                readBookControl.setParagraphSize(1.5f);
                callback.upTextSize();
                break;
            case R.id.tvRowDef1:
                readBookControl.setLineMultiplier(1.2f);
                readBookControl.setParagraphSize(1.8f);
                callback.upTextSize();
                break;
            case R.id.tvRowDef2:
                readBookControl.setLineMultiplier(1.8f);
                readBookControl.setParagraphSize(2.0f);
                callback.upTextSize();
                break;
            case R.id.tvRowDef:
                readBookControl.setLineMultiplier(1.0f);
                readBookControl.setParagraphSize(1.8f);
                callback.upTextSize();
                break;
            case R.id.tvOther:
                break;
            case R.id.civ_bg_white:
                updateBg(0);
                callback.bgChange();
                break;
            case R.id.civ_bg_yellow:
                updateBg(1);
                callback.bgChange();
                break;
            case R.id.civ_bg_green:
                updateBg(2);
                callback.bgChange();
                break;
            case R.id.civ_bg_blue:
                updateBg(3);
                callback.bgChange();
                break;
            case R.id.civ_bg_black:
                updateBg(4);
                callback.bgChange();
                break;
        }
    }

    public interface Callback {
        void upPageMode();

        void upTextSize();


        void bgChange();

        void refresh();
    }

    public void setBg() {
        tv0.setTextColor(readBookControl.getTextColor(0));
        tv1.setTextColor(readBookControl.getTextColor(1));
        tv2.setTextColor(readBookControl.getTextColor(2));
        tv3.setTextColor(readBookControl.getTextColor(3));
        tv4.setTextColor(readBookControl.getTextColor(4));
        civBgWhite.setImageDrawable(readBookControl.getBgDrawable(0, activity, 100, 180));
        civBgYellow.setImageDrawable(readBookControl.getBgDrawable(1, activity, 100, 180));
        civBgGreen.setImageDrawable(readBookControl.getBgDrawable(2, activity, 100, 180));
        civBgBlue.setImageDrawable(readBookControl.getBgDrawable(3, activity, 100, 180));
        civBgBlack.setImageDrawable(readBookControl.getBgDrawable(4, activity, 100, 180));
    }

    private void updateBg(int index) {
        civBgWhite.setBorderColor(activity.getResources().getColor(R.color.tv_text_default));
        civBgYellow.setBorderColor(activity.getResources().getColor(R.color.tv_text_default));
        civBgGreen.setBorderColor(activity.getResources().getColor(R.color.tv_text_default));
        civBgBlack.setBorderColor(activity.getResources().getColor(R.color.tv_text_default));
        civBgBlue.setBorderColor(activity.getResources().getColor(R.color.tv_text_default));
        switch (index) {
            case 0:
                civBgWhite.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 1:
                civBgYellow.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 2:
                civBgGreen.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 3:
                civBgBlue.setBorderColor(Color.parseColor("#F3B63F"));
                break;
            case 4:
                civBgBlack.setBorderColor(Color.parseColor("#F3B63F"));
                break;
        }
        readBookControl.setTextDrawableIndex(index);
    }

    private void updatePageMode(int pageMode) {
        tvPageMode.setText(String.format("%s", PageAnimation.Mode.getPageMode(pageMode)));
    }

    private void updateBoldText(Boolean isBold) {
        flTextBold.setSelected(isBold);
    }


    //清除字体
    private void clearFontPath() {
        readBookControl.setReadBookFont(null);
        callback.refresh();
    }

    //设置字体
    public void setReadFonts(String path) {
        readBookControl.setReadBookFont(path);
        callback.refresh();
    }

}
