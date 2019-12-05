package com.kzw.leisure.bean;

import java.io.Serializable;

/**
 * author: kang4
 * Date: 2019/12/4
 * Description:
 */
public class SearchBookBean implements Serializable {

    private String searchAuthor;
    private String searchCoverUrl;
    private String searchIntroduce;
    private String searchKind;
    private String searchLastChapter;
    private String searchName;
    private String searchNoteUrl;



    public String getSearchAuthor() {
        return searchAuthor;
    }

    public void setSearchAuthor(String searchAuthor) {
        this.searchAuthor = searchAuthor;
    }

    public String getSearchCoverUrl() {
        return searchCoverUrl;
    }

    public void setSearchCoverUrl(String searchCoverUrl) {
        this.searchCoverUrl = searchCoverUrl;
    }

    public String getSearchIntroduce() {
        return searchIntroduce;
    }

    public void setSearchIntroduce(String searchIntroduce) {
        this.searchIntroduce = searchIntroduce;
    }

    public String getSearchKind() {
        return searchKind;
    }

    public void setSearchKind(String searchKind) {
        this.searchKind = searchKind;
    }

    public String getSearchLastChapter() {
        return searchLastChapter;
    }

    public void setSearchLastChapter(String searchLastChapter) {
        this.searchLastChapter = searchLastChapter;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchNoteUrl() {
        return searchNoteUrl;
    }

    public void setSearchNoteUrl(String searchNoteUrl) {
        this.searchNoteUrl = searchNoteUrl;
    }
}
