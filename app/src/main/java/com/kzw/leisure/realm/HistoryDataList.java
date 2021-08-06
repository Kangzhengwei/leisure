package com.kzw.leisure.realm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HistoryDataList extends RealmObject {
    public RealmList<HistoryDataBean> historyDataBeanRealmList = new RealmList<>();

    public void setHistoryDataBeanRealmList(List<HistoryDataBean> historyDataBeanRealmList) {
        this.historyDataBeanRealmList.addAll(historyDataBeanRealmList);
    }

    public RealmList<HistoryDataBean> getHistoryDataBeanRealmList() {
        return historyDataBeanRealmList;
    }
}
