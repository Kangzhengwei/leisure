package com.kzw.leisure.bean;

import java.util.Map;


/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public class QuerySearchVideoBean  {

    private Map<String, String> params;
    private String siteName;
    private String ruleSearchList;
    private String ruleSearchName;
    private String ruleSearchNoteUrl;
    private String ruleSearchUrl;
    private String videoSourceUrl;
    private String ruleSeriesList;
    private String ruleSeriesName;
    private String ruleSeriesNoteUrl;
    private String rulePlayType;
    private String ruleItem;
    private String ruleTypeList;
    private String ruleVideoName;
    private String ruleVideoImage;
    private int sourceType;//分为列表式和父子式

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
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

    public String getRuleTypeList() {
        return ruleTypeList;
    }

    public void setRuleTypeList(String ruleTypeList) {
        this.ruleTypeList = ruleTypeList;
    }

    public String getRuleItem() {
        return ruleItem;
    }

    public void setRuleItem(String ruleItem) {
        this.ruleItem = ruleItem;
    }

    public String getRulePlayType() {
        return rulePlayType;
    }

    public void setRulePlayType(String rulePlayType) {
        this.rulePlayType = rulePlayType;
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

    public QuerySearchVideoBean(Map<String, String> params, String siteName, String ruleSearchList, String ruleSearchName, String ruleSearchNoteUrl, String videoSourceUrl, String ruleSearchUrl) {
        //this.params = params;
        this.siteName = siteName;
        this.ruleSearchList = ruleSearchList;
        this.ruleSearchName = ruleSearchName;
        this.ruleSearchNoteUrl = ruleSearchNoteUrl;
        this.videoSourceUrl = videoSourceUrl;
        this.ruleSearchUrl = ruleSearchUrl;
    }

   public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getRuleSearchList() {
        return ruleSearchList;
    }

    public void setRuleSearchList(String ruleSearchList) {
        this.ruleSearchList = ruleSearchList;
    }

    public String getRuleSearchName() {
        return ruleSearchName;
    }

    public void setRuleSearchName(String ruleSearchName) {
        this.ruleSearchName = ruleSearchName;
    }

    public String getRuleSearchNoteUrl() {
        return ruleSearchNoteUrl;
    }

    public void setRuleSearchNoteUrl(String ruleSearchNoteUrl) {
        this.ruleSearchNoteUrl = ruleSearchNoteUrl;
    }

    public String getRuleSearchUrl() {
        return ruleSearchUrl;
    }

    public void setRuleSearchUrl(String ruleSearchUrl) {
        this.ruleSearchUrl = ruleSearchUrl;
    }

    public String getVideoSourceUrl() {
        return videoSourceUrl;
    }

    public void setVideoSourceUrl(String videoSourceUrl) {
        this.videoSourceUrl = videoSourceUrl;
    }
}
