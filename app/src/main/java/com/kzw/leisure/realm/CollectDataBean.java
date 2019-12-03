package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * Created by Android on 2019/2/27.
 */

public class CollectDataBean extends RealmObject {

    public String urlTitle;
    public String url;

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
