package com.kzw.leisure.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private BookSourceRule currentSearchRule;

    private List<String> searchNoteUrlList = new ArrayList<>();
    private List<BookSourceRule> searchRuleList = new ArrayList<>();


    public BookSourceRule getCurrentSearchRule() {
        return currentSearchRule;
    }

    public void setCurrentSearchRule(BookSourceRule currentSearchRule) {
        this.currentSearchRule = currentSearchRule;
    }

    public List<String> getSearchNoteUrlList() {
        return searchNoteUrlList;
    }

    public void setSearchNoteUrlList(List<String> searchNoteUrlList) {
        this.searchNoteUrlList = searchNoteUrlList;
    }

    public List<BookSourceRule> getSearchRuleList() {
        return searchRuleList;
    }

    public void setSearchRuleList(List<BookSourceRule> searchRuleList) {
        this.searchRuleList = searchRuleList;
    }

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


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final SearchBookBean bean = (SearchBookBean) obj;
        if (searchAuthor.equals(bean.getSearchAuthor()) && searchName.equals(bean.getSearchName())) {
            if (!TextUtils.isEmpty(searchCoverUrl)) {
                bean.setSearchCoverUrl(searchCoverUrl);
            }
            bean.getSearchRuleList().addAll(searchRuleList);
            bean.getSearchNoteUrlList().addAll(searchNoteUrlList);
        }
        return searchAuthor.equals(bean.getSearchAuthor()) && searchName.equals(bean.getSearchName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchAuthor, searchName);
    }

    @Override
    public String toString() {
        return "User{" +
                "searchAuthor='" + searchAuthor + '\'' +
                ", searchName=" + searchName +
                '}';
    }
}
