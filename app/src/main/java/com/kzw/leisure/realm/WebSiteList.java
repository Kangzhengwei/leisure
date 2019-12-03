package com.kzw.leisure.realm;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Android on 2019/2/28.
 */

public class WebSiteList extends RealmObject {

    public RealmList<WebSiteBean> webSiteBeanRealmList = new RealmList<>();

    public void setWebSiteBeanRealmList(List<WebSiteBean> webSiteBeanRealmList) {
        this.webSiteBeanRealmList.addAll(webSiteBeanRealmList);
    }

    public RealmList<WebSiteBean> getWebSiteBeanRealmList() {
        return webSiteBeanRealmList;
    }
}
