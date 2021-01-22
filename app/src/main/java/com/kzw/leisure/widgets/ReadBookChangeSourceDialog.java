package com.kzw.leisure.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.ReadBookSourceAdapter;
import com.kzw.leisure.realm.SourceRuleRealm;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author: kang4
 * Date: 2019/12/12
 * Description:
 */
public class ReadBookChangeSourceDialog extends Dialog {

    itemClickListener mListener;

    ReadBookSourceAdapter adapter;

    public static ReadBookChangeSourceDialog builder(Context context, SourceRuleRealm sourceRuleRealm) {
        return new ReadBookChangeSourceDialog(context, sourceRuleRealm);
    }

    public ReadBookChangeSourceDialog setList(List<SourceRuleRealm> list) {
        adapter.setNewData(list);
        return this;
    }

    public ReadBookChangeSourceDialog(Context context, SourceRuleRealm sourceRuleRealm) {
        super(context, R.style.alertDialogTheme);
        init(context, sourceRuleRealm);
    }

    private void init(Context context, SourceRuleRealm sourceRuleRealm) {
        View mView = LayoutInflater.from(context).inflate(R.layout.read_book_change_source_dialog, null);
        setContentView(mView);
        RecyclerView recyclerView = mView.findViewById(R.id.source_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReadBookSourceAdapter(sourceRuleRealm);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            dismiss();
            Object object = adapter.getData().get(position);
            if (object instanceof SourceRuleRealm) {
                SourceRuleRealm sourceRule = (SourceRuleRealm) object;
                if (mListener != null) {
                    mListener.itemClick(sourceRule,position);
                }
            }
        });
    }

    @Override
    public void show() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.show();
        fullScreenImmersive(getWindow().getDecorView());
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    public interface itemClickListener {
        void itemClick(SourceRuleRealm bean,int position);
    }

    public ReadBookChangeSourceDialog setListener(itemClickListener listener) {
        mListener = listener;
        return this;
    }

    private void fullScreenImmersive(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            view.setSystemUiVisibility(uiOptions);
        }
    }
}
