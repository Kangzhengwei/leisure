package com.kzw.leisure.bean;

import android.text.TextUtils;

import com.kzw.leisure.utils.StringUtils;
import com.kzw.leisure.utils.UrlEncoderUtils;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author: kang4
 * Date: 2019/12/4
 * Description:
 */
public class BookSourceRule implements Serializable {

    private Map<String, String> queryMap = new LinkedHashMap<>();

    private String siteName;
    private String baseUrl;

    private String ruleSearchAuthor;
    private String ruleSearchCoverUrl;
    private String ruleSearchIntroduce;
    private String ruleSearchKind;
    private String ruleSearchLastChapter;
    private String ruleSearchList;
    private String ruleSearchName;
    private String ruleSearchNoteUrl;
    private String ruleSearchUrl;

    private String ruleBookAuthor;
    private String ruleBookContent;
    private String ruleBookInfoInit;
    private String ruleBookKind;
    private String ruleBookLastChapter;
    private String ruleBookName;
    private String ruleBookUrlPattern;
    private String ruleCoverUrl;
    private String ruleChapterList;

    private String ruleChapterName;
    private String ruleChapterUrl;
    private String ruleChapterUrlNext;
    private String ruleContentUrl;
    private String ruleContentUrlNext;

    private String charCode = null;

    public void setMap(String key) throws Exception {
        if (!StringUtils.isTrimEmpty(key)) {
            ruleSearchUrl = ruleSearchUrl.replace("ruleKeyword", key);
        }
        ruleSearchUrl = splitCharCode(ruleSearchUrl);
        String[] ruleUrlS = ruleSearchUrl.split("@");
        ruleSearchUrl = ruleUrlS[0];
        analyzeQuery(ruleUrlS[1]);
    }


    public String getRuleBookAuthor() {
        return ruleBookAuthor;
    }

    public void setRuleBookAuthor(String ruleBookAuthor) {
        this.ruleBookAuthor = ruleBookAuthor;
    }

    public String getRuleBookContent() {
        return ruleBookContent;
    }

    public void setRuleBookContent(String ruleBookContent) {
        this.ruleBookContent = ruleBookContent;
    }

    public String getRuleBookInfoInit() {
        return ruleBookInfoInit;
    }

    public void setRuleBookInfoInit(String ruleBookInfoInit) {
        this.ruleBookInfoInit = ruleBookInfoInit;
    }

    public String getRuleBookKind() {
        return ruleBookKind;
    }

    public void setRuleBookKind(String ruleBookKind) {
        this.ruleBookKind = ruleBookKind;
    }

    public String getRuleBookLastChapter() {
        return ruleBookLastChapter;
    }

    public void setRuleBookLastChapter(String ruleBookLastChapter) {
        this.ruleBookLastChapter = ruleBookLastChapter;
    }

    public String getRuleBookName() {
        return ruleBookName;
    }

    public void setRuleBookName(String ruleBookName) {
        this.ruleBookName = ruleBookName;
    }

    public String getRuleBookUrlPattern() {
        return ruleBookUrlPattern;
    }

    public void setRuleBookUrlPattern(String ruleBookUrlPattern) {
        this.ruleBookUrlPattern = ruleBookUrlPattern;
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

    public String getRuleCoverUrl() {
        return ruleCoverUrl;
    }

    public void setRuleCoverUrl(String ruleCoverUrl) {
        this.ruleCoverUrl = ruleCoverUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Map<String, String> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRuleSearchAuthor() {
        return ruleSearchAuthor;
    }

    public void setRuleSearchAuthor(String ruleSearchAuthor) {
        this.ruleSearchAuthor = ruleSearchAuthor;
    }

    public String getRuleSearchCoverUrl() {
        return ruleSearchCoverUrl;
    }

    public void setRuleSearchCoverUrl(String ruleSearchCoverUrl) {
        this.ruleSearchCoverUrl = ruleSearchCoverUrl;
    }

    public String getRuleSearchIntroduce() {
        return ruleSearchIntroduce;
    }

    public void setRuleSearchIntroduce(String ruleSearchIntroduce) {
        this.ruleSearchIntroduce = ruleSearchIntroduce;
    }

    public String getRuleSearchKind() {
        return ruleSearchKind;
    }

    public void setRuleSearchKind(String ruleSearchKind) {
        this.ruleSearchKind = ruleSearchKind;
    }

    public String getRuleSearchLastChapter() {
        return ruleSearchLastChapter;
    }

    public void setRuleSearchLastChapter(String ruleSearchLastChapter) {
        this.ruleSearchLastChapter = ruleSearchLastChapter;
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

    private void analyzeQuery(String allQuery) throws Exception {
        String[] queryS = allQuery.split("&");
        for (String query : queryS) {
            String[] queryM = query.split("=");
            String value = queryM.length > 1 ? queryM[1] : "";
            if (TextUtils.isEmpty(charCode)) {
                if (UrlEncoderUtils.hasUrlEncoded(value)) {
                    queryMap.put(queryM[0], value);
                } else {
                    queryMap.put(queryM[0], URLEncoder.encode(value, "UTF-8"));
                }
            } else if (charCode.equals("escape")) {
                queryMap.put(queryM[0], StringUtils.escape(value));
            } else {
                queryMap.put(queryM[0], URLEncoder.encode(value, charCode));
            }
        }
    }

    /**
     * 分离编码规则
     */
    private String splitCharCode(String rule) {
        String[] ruleUrlS = rule.split("\\|");
        if (ruleUrlS.length > 1) {
            if (!TextUtils.isEmpty(ruleUrlS[1])) {
                String[] qtS = ruleUrlS[1].split("&");
                for (String qt : qtS) {
                    String[] gz = qt.split("=");
                    if (gz[0].equals("char")) {
                        charCode = gz[1];
                    }
                }
            }
        }
        return ruleUrlS[0];
    }
}
