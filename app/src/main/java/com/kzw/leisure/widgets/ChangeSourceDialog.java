package com.kzw.leisure.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kzw.leisure.R;
import com.kzw.leisure.adapter.SourceAdapter;
import com.kzw.leisure.bean.BookSourceRule;
import com.kzw.leisure.utils.SPUtils;

import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class ChangeSourceDialog extends Dialog {

    SourceAdapter adapter;
    itemClickListener mListener;

    public static ChangeSourceDialog builder(Context context) {
        return new ChangeSourceDialog(context);
    }

    public ChangeSourceDialog setList(List<BookSourceRule> list) {
        adapter.setNewData(list);
        return this;
    }

    public ChangeSourceDialog(Context context) {
        super(context, R.style.alertDialogTheme);
        init(context);
    }

    private void init(Context context) {
        View mView = LayoutInflater.from(context).inflate(R.layout.change_source_dialog, null);
        setContentView(mView);
        RecyclerView recyclerView = mView.findViewById(R.id.source_list);
        RelativeLayout allLayout = mView.findViewById(R.id.all_layout);
        RadioButton radioButton = mView.findViewById(R.id.all_radiobt);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SourceAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            dismiss();
            Object object = adapter.getData().get(position);
            if (object instanceof BookSourceRule) {
                BookSourceRule sourceRule = (BookSourceRule) object;
                if (mListener != null) {
                    mListener.itemClick(sourceRule);
                }
            }
        });
        allLayout.setOnClickListener(view -> {
            dismiss();
            radioButton.setChecked(true);
            SPUtils.getInstance().putObject("defaultRule", null);
            if (mListener != null) {
                mListener.allRule();
            }
        });
        BookSourceRule rule = SPUtils.getInstance().getObject("defaultRule", BookSourceRule.class);
        if (rule == null) {
            radioButton.setChecked(true);
        } else {
            radioButton.setChecked(false);
        }
        radioButton.setClickable(false);
    }

    public interface itemClickListener {
        void itemClick(BookSourceRule bean);

        void allRule();
    }

    public ChangeSourceDialog setListener(itemClickListener listener) {
        mListener = listener;
        return this;
    }
}
