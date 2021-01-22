package com.kzw.leisure.bean;

import com.kzw.leisure.realm.SourceRuleRealm;

import java.io.Serializable;

/**
 * author: kang4
 * Date: 2019/12/12
 * Description:
 */
public class ChapterRule implements Serializable {
    private String ruleChapterList;
    private String ruleChapterName;
    private String ruleChapterUrl;
    private String ruleChapterUrlNext;
    private String ruleContentUrl;
    private String ruleContentUrlNext;
    private int ruleChapterUrlType;
    private String baseUrl;
    private String siteName;


    public ChapterRule(SourceRuleRealm ruleRealm) {
        this.ruleChapterList = ruleRealm.getRuleChapterList();
        this.ruleChapterName = ruleRealm.getRuleChapterName();
        this.ruleChapterUrl = ruleRealm.getRuleChapterUrl();
        this.ruleChapterUrlNext = ruleRealm.getRuleChapterUrlNext();
        this.ruleContentUrl = ruleRealm.getRuleContentUrl();
        this.ruleContentUrlNext = ruleRealm.getRuleContentUrlNext();
        this.ruleChapterUrlType = ruleRealm.getRuleChapterUrlType();
        this.baseUrl = ruleRealm.getBaseUrl();
        this.siteName = ruleRealm.getSiteName();
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        return "ChapterRule{" +
                "ruleChapterList='" + ruleChapterList + '\'' +
                ", ruleChapterName='" + ruleChapterName + '\'' +
                ", ruleChapterUrl='" + ruleChapterUrl + '\'' +
                ", ruleChapterUrlNext='" + ruleChapterUrlNext + '\'' +
                ", ruleContentUrl='" + ruleContentUrl + '\'' +
                ", ruleContentUrlNext='" + ruleContentUrlNext + '\'' +
                ", ruleChapterUrlType=" + ruleChapterUrlType +
                ", baseUrl='" + baseUrl + '\'' +
                ", siteName='" + siteName + '\'' +
                '}';
    }
}
