package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2021/1/19
 * Description:
 */
public class VideoWatchTypeSeriesRealm extends RealmObject {
    public String seriesName;
    public int seriesNum;

    public VideoWatchTypeSeriesRealm(){
    }
    public VideoWatchTypeSeriesRealm(String seriesName,int seriesNum){
        this.seriesName=seriesName;
        this.seriesNum=seriesNum;
    }
    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(int seriesNum) {
        this.seriesNum = seriesNum;
    }
}
