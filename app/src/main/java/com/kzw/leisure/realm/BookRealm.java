package com.kzw.leisure.realm;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/5
 * Description:
 */
public class BookRealm extends RealmObject implements Serializable {

    private String bookAuthor = "";
    private String bookContent = "";
    private String bookInfoInit = "";
    private String bookKind = "";
    private String bookLastChapter = "";
    private String bookName = "";
    private String bookUrlPattern = "";
    private String coverUrl = "";
    private String durChapterUrl = "";
    private String chapterListUrl = "";
    private String durChapterName = "";
    private Integer durChapter = 0;   //当前章节 （包括番外）
    private Integer durChapterPage = 0;  // 当前章节位置   用页码
    private Long finalDate = System.currentTimeMillis();  //最后阅读时间
    private Boolean hasUpdate = false;  //是否有更新
    private Integer newChapters = 0;  //更新章节数
    private String tag = "";
    private Integer serialNumber = 0; //手动排序
    private Long finalRefreshData = System.currentTimeMillis();  //章节最后更新时间
    private Integer group = 0;
    private String lastChapterName = "";
    private Integer chapterListSize = 0;
    private Boolean allowUpdate = true;
    private Boolean useReplaceRule = true;
    private String variable = "";
    private RealmList<ChapterList> searchNoteUrlList = new RealmList<>();
    private RealmList<SourceRuleRealm> sourceRuleRealmList = new RealmList<>();
    private SourceRuleRealm currentRule;
    private ChapterList currentChapterListRule;

    public ChapterList getCurrentChapterListRule() {
        return currentChapterListRule;
    }

    public void setCurrentChapterListRule(ChapterList currentChapterListRule) {
        this.currentChapterListRule = currentChapterListRule;
    }

    public SourceRuleRealm getCurrentRule() {
        return currentRule;
    }

    public void setCurrentRule(SourceRuleRealm currentRule) {
        this.currentRule = currentRule;
    }

    public RealmList<SourceRuleRealm> getSourceRuleRealmList() {
        return sourceRuleRealmList;
    }

    public void setSourceRuleRealmList(List<SourceRuleRealm> sourceRuleRealmList) {
        this.sourceRuleRealmList.addAll(sourceRuleRealmList);
    }

    public RealmList<ChapterList> getSearchNoteUrlList() {
        return searchNoteUrlList;
    }

    public void setSearchNoteUrlList(List<ChapterList> searchNoteUrlList) {
        this.searchNoteUrlList.addAll(searchNoteUrlList);
    }

    public Integer getDurChapter() {
        return durChapter;
    }

    public void setDurChapter(Integer durChapter) {
        this.durChapter = durChapter;
    }

    public void setDurChapterPage(Integer durChapterPage) {
        this.durChapterPage = durChapterPage;
    }

    public Long getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Long finalDate) {
        this.finalDate = finalDate;
    }

    public Boolean getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(Boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public Integer getNewChapters() {
        return newChapters;
    }

    public void setNewChapters(Integer newChapters) {
        this.newChapters = newChapters;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getFinalRefreshData() {
        return finalRefreshData;
    }

    public void setFinalRefreshData(Long finalRefreshData) {
        this.finalRefreshData = finalRefreshData;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getDurChapterName() {
        return durChapterName;
    }

    public void setDurChapterName(String durChapterName) {
        this.durChapterName = durChapterName;
    }

    public String getLastChapterName() {
        return lastChapterName;
    }

    public void setLastChapterName(String lastChapterName) {
        this.lastChapterName = lastChapterName;
    }

    public Integer getChapterListSize() {
        return chapterListSize;
    }

    public void setChapterListSize(Integer chapterListSize) {
        this.chapterListSize = chapterListSize;
    }

    public Boolean getAllowUpdate() {
        return allowUpdate;
    }

    public void setAllowUpdate(Boolean allowUpdate) {
        this.allowUpdate = allowUpdate;
    }

    public Boolean getUseReplaceRule() {
        return useReplaceRule;
    }

    public void setUseReplaceRule(Boolean useReplaceRule) {
        this.useReplaceRule = useReplaceRule;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

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


    public String getDurChapterUrl() {
        return durChapterUrl;
    }

    public void setDurChapterUrl(String url) {
        this.durChapterUrl = url;
    }


    public int getDurChapterPage() {
        return durChapterPage;
    }

}
