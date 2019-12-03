package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * Created by Android on 2019/2/26.
 */

public class WebSiteBean extends RealmObject {
    public String siteName;
    public String url;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
