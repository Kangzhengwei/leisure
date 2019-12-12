package com.kzw.leisure.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

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

    public interface itemClickListener {
        void itemClick(SourceRuleRealm bean,int position);
    }

    public ReadBookChangeSourceDialog setListener(itemClickListener listener) {
        mListener = listener;
        return this;
    }
}
