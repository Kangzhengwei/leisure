package com.kzw.leisure.realm;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/3
 * Description:
 */
public class VideoRealm extends RealmObject implements Serializable {

    public String name;
    private String videoImage;
    public String url;

    public String videoSourceUrl;
    private String ruleSeriesList;
    private String ruleSeriesName;
    private String ruleSeriesNoteUrl;
    private String rulePlayType;
    private String ruleItem;
    private String ruleTypeList;
    private String ruleVideoName;
    private String ruleVideoImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoSourceUrl() {
        return videoSourceUrl;
    }

    public void setVideoSourceUrl(String videoSourceUrl) {
        this.videoSourceUrl = videoSourceUrl;
    }

    public String getRuleSeriesList() {
        return ruleSeriesList;
    }

    public void setRuleSeriesList(String ruleSeriesList) {
        this.ruleSeriesList = ruleSeriesList;
    }

    public String getRuleSeriesName() {
        return ruleSeriesName;
    }

    public void setRuleSeriesName(String ruleSeriesName) {
        this.ruleSeriesName = ruleSeriesName;
    }

    public String getRuleSeriesNoteUrl() {
        return ruleSeriesNoteUrl;
    }

    public void setRuleSeriesNoteUrl(String ruleSeriesNoteUrl) {
        this.ruleSeriesNoteUrl = ruleSeriesNoteUrl;
    }

    public String getRulePlayType() {
        return rulePlayType;
    }

    public void setRulePlayType(String rulePlayType) {
        this.rulePlayType = rulePlayType;
    }

    public String getRuleItem() {
        return ruleItem;
    }

    public void setRuleItem(String ruleItem) {
        this.ruleItem = ruleItem;
    }

    public String getRuleTypeList() {
        return ruleTypeList;
    }

    public void setRuleTypeList(String ruleTypeList) {
        this.ruleTypeList = ruleTypeList;
    }

    public String getRuleVideoName() {
        return ruleVideoName;
    }

    public void setRuleVideoName(String ruleVideoName) {
        this.ruleVideoName = ruleVideoName;
    }

    public String getRuleVideoImage() {
        return ruleVideoImage;
    }

    public void setRuleVideoImage(String ruleVideoImage) {
        this.ruleVideoImage = ruleVideoImage;
    }
}
