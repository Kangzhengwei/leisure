package com.kzw.leisure.realm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/11/21
 * Description:
 */
public class CollectDataList extends RealmObject {

    public RealmList<CollectDataBean> collectList = new RealmList<>();

    public void setCollectList(List<CollectDataBean> webSiteBeanRealmList) {
        this.collectList.addAll(webSiteBeanRealmList);
    }

    public RealmList<CollectDataBean> getCollectList() {
        return collectList;
    }
}
