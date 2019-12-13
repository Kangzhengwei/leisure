package com.kzw.leisure.widgets.font;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import com.kzw.leisure.R;
import com.kzw.leisure.utils.DocumentHelper;
import com.kzw.leisure.utils.FileUtils;
import com.kzw.leisure.utils.theme.ATH;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FontSelector {
    private AlertDialog.Builder builder;
    private FontAdapter adapter;
    private String fontPath;
    private OnThisListener thisListener;
    private AlertDialog alertDialog;
    private Activity activity;

    public FontSelector(Activity context, String selectPath) {
        this.activity = context;
        builder = new AlertDialog.Builder(context, R.style.alertDialogTheme);
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.view_recycler_font, null);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        builder.setView(view);
        builder.setTitle(R.string.select_font);
        builder.setNegativeButton(R.string.go_dwnload, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                downloadByBrowser("https://www.jianguoyun.com/p/DXyqo6IQ0Zb6Bxj2ibEC");//https://www.jianguoyun.com/p/DXyqo6IQ0Zb6Bxj2ibEC//"https://cloud.189.cn/t/muAnquYfMJ3q";
            }
        });
        fontPath = FileUtils.getSdCardPath() + "/Fonts";
        adapter = new FontAdapter(context, selectPath,
                new OnThisListener() {
                    @Override
                    public void setDefault() {
                        if (thisListener != null) {
                            thisListener.setDefault();
                        }
                        alertDialog.dismiss();
                    }

                    @Override
                    public void setFontPath(String fontPath) {
                        if (thisListener != null) {
                            thisListener.setFontPath(fontPath);
                        }
                        alertDialog.dismiss();
                    }
                });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public FontSelector setListener(OnThisListener thisListener) {
        this.thisListener = thisListener;
        builder.setPositiveButton(R.string.default_font, ((dialogInterface, i) -> thisListener.setDefault()));
        return this;
    }

    public FontSelector setPath(String path) {
        fontPath = path;
        return this;
    }

    public FontSelector create() {
        adapter.upData(getFontFiles());
        builder.create();
        return this;
    }

    public void show() {
        alertDialog = builder.show();
        ATH.setAlertDialogTint(alertDialog);
    }

    private File[] getFontFiles() {
        try {
            DocumentHelper.createDirIfNotExist(fontPath);
            File file = new File(fontPath);
            return file.listFiles(pathName -> pathName.getName().toLowerCase().matches(".*\\.[ot]tf"));
        } catch (Exception e) {
            return null;
        }
    }

    public interface OnThisListener {
        void setDefault();

        void setFontPath(String fontPath);
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }
}
