package com.kzw.leisure.realm;

import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2019/12/12
 * Description:
 */
public class ChapterList extends RealmObject {
    private String chapterListUrlRule;

    private String chapterListCache;//以string 类型保存html文件

    public String getChapterListUrlRule() {
        return chapterListUrlRule;
    }

    public void setChapterListUrlRule(String chapterListUrlRule) {
        this.chapterListUrlRule = chapterListUrlRule;
    }

    public String getChapterListCache() {
        return chapterListCache;
    }

    public void setChapterListCache(String chapterListCache) {
        this.chapterListCache = chapterListCache;
    }
}
