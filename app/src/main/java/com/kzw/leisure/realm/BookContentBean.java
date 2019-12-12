package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/9
 * Description:
 */
public class BookContentBean extends RealmObject {

    private String noteUrl; //对应BookInfoBean noteUrl;

    private String durChapterUrl;

    private Integer durChapterIndex;   //当前章节  （包括番外）

    private String durChapterContent; //当前章节内容

    private String tag;   //来源  某个网站/本地

    private Long timeMillis;



    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public String getDurChapterUrl() {
        return durChapterUrl;
    }

    public void setDurChapterUrl(String durChapterUrl) {
        this.durChapterUrl = durChapterUrl;
    }

    public Integer getDurChapterIndex() {
        return durChapterIndex;
    }

    public void setDurChapterIndex(Integer durChapterIndex) {
        this.durChapterIndex = durChapterIndex;
    }

    public String getDurChapterContent() {
        return durChapterContent;
    }

    public void setDurChapterContent(String durChapterContent) {
        this.durChapterContent = durChapterContent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }
}
