package com.kzw.leisure.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2021/1/19
 * Description:
 */
public class VideoWatchTypeRealm extends RealmObject {

    public String urlType;
    public RealmList<VideoWatchTypeSeriesRealm> mList=new RealmList<>();

    public VideoWatchTypeRealm(){

    }
    public VideoWatchTypeRealm(String urlType){
        this.urlType=urlType;
    }
    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public RealmList<VideoWatchTypeSeriesRealm> getmList() {
        return mList;
    }

    public void setmList(RealmList<VideoWatchTypeSeriesRealm> mList) {
        this.mList = mList;
    }
}
