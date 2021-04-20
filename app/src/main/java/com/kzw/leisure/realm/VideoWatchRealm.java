package com.kzw.leisure.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2021/1/19
 * Description:
 */
public class VideoWatchRealm extends RealmObject {

    public String videoName;
    public RealmList<VideoWatchTypeRealm> mlist=new RealmList<>();

    public VideoWatchRealm(){

    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public RealmList<VideoWatchTypeRealm> getMlist() {
        return mlist;
    }

    public void setMlist(RealmList<VideoWatchTypeRealm> mlist) {
        this.mlist = mlist;
    }
}
