package com.kzw.leisure.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class BookBean implements Serializable {

    private String bookAuthor;
    private String bookContent;
    private String bookInfoInit;
    private String bookKind;
    private String bookLastChapter;
    private String bookName;
    private String bookUrlPattern;
    private String coverUrl;
    private List<Chapter> list;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public String getBookInfoInit() {
        return bookInfoInit;
    }

    public void setBookInfoInit(String bookInfoInit) {
        this.bookInfoInit = bookInfoInit;
    }

    public String getBookKind() {
        return bookKind;
    }

    public void setBookKind(String bookKind) {
        this.bookKind = bookKind;
    }

    public String getBookLastChapter() {
        return bookLastChapter;
    }

    public void setBookLastChapter(String bookLastChapter) {
        this.bookLastChapter = bookLastChapter;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookUrlPattern() {
        return bookUrlPattern;
    }

    public void setBookUrlPattern(String bookUrlPattern) {
        this.bookUrlPattern = bookUrlPattern;
    }

    public List<Chapter> getList() {
        return list;
    }

    public void setList(List<Chapter> list) {
        this.list = list;
    }
}
