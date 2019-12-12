package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/11
 * Description:
 */
public class SourceRuleRealm extends RealmObject {

    private String ruleChapterList;
    private String ruleChapterName;
    private String ruleChapterUrl;
    private String ruleChapterUrlNext;
    private String ruleContentUrl;
    private String ruleContentUrlNext;
    private int ruleChapterUrlType;
    private String baseUrl;
    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRuleChapterList() {
        return ruleChapterList;
    }

    public void setRuleChapterList(String ruleChapterList) {
        this.ruleChapterList = ruleChapterList;
    }

    public String getRuleChapterName() {
        return ruleChapterName;
    }

    public void setRuleChapterName(String ruleChapterName) {
        this.ruleChapterName = ruleChapterName;
    }

    public String getRuleChapterUrl() {
        return ruleChapterUrl;
    }

    public void setRuleChapterUrl(String ruleChapterUrl) {
        this.ruleChapterUrl = ruleChapterUrl;
    }

    public String getRuleChapterUrlNext() {
        return ruleChapterUrlNext;
    }

    public void setRuleChapterUrlNext(String ruleChapterUrlNext) {
        this.ruleChapterUrlNext = ruleChapterUrlNext;
    }

    public String getRuleContentUrl() {
        return ruleContentUrl;
    }

    public void setRuleContentUrl(String ruleContentUrl) {
        this.ruleContentUrl = ruleContentUrl;
    }

    public String getRuleContentUrlNext() {
        return ruleContentUrlNext;
    }

    public void setRuleContentUrlNext(String ruleContentUrlNext) {
        this.ruleContentUrlNext = ruleContentUrlNext;
    }

    public int getRuleChapterUrlType() {
        return ruleChapterUrlType;
    }

    public void setRuleChapterUrlType(int ruleChapterUrlType) {
        this.ruleChapterUrlType = ruleChapterUrlType;
    }
}
