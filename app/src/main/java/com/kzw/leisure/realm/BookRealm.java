package com.kzw.leisure.realm;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class BookRealm extends RealmObject implements Serializable {

    private String bookAuthor;
    private String bookContent;
    private String bookInfoInit;
    private String bookKind;
    private String bookLastChapter;
    private String bookName;
    private String bookUrlPattern;
    private String coverUrl;
    private String chapterName;
    private String url;
    private String chapterListUrl;
    private int position;//目前章节数
    private int durChapterPage = 0;  // 当前章节位置   用页码

    public String getChapterListUrl() {
        return chapterListUrl;
    }

    public void setChapterListUrl(String chapterListUrl) {
        this.chapterListUrl = chapterListUrl;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDurChapterPage() {
        return durChapterPage;
    }

    public void setDurChapterPage(int durChapterPage) {
        this.durChapterPage = durChapterPage;
    }
}
