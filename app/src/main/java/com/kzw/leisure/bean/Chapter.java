package com.kzw.leisure.bean;

import java.io.Serializable;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class Chapter implements Serializable {

    private String chapterName;
    private String chapterUrl;
    private String chapterUrlNext;
    private String contentUrl;
    private String contentUrlNext;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public String getChapterUrlNext() {
        return chapterUrlNext;
    }

    public void setChapterUrlNext(String chapterUrlNext) {
        this.chapterUrlNext = chapterUrlNext;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentUrlNext() {
        return contentUrlNext;
    }

    public void setContentUrlNext(String contentUrlNext) {
        this.contentUrlNext = contentUrlNext;
    }
}
