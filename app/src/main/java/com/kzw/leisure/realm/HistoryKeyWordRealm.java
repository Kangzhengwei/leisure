package com.kzw.leisure.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * author: kang4
 * Date: 2021/1/21
 * Description:
 */
public class HistoryKeyWordRealm extends RealmObject {
    public int type;//0为视频搜索，1为书籍搜索

    public RealmList<String> stringRealmList=new RealmList<>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RealmList<String> getStringRealmList() {
        return stringRealmList;
    }

    public void setStringRealmList(RealmList<String> stringRealmList) {
        this.stringRealmList = stringRealmList;
    }
}
