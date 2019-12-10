package com.kzw.leisure.utils;


import com.kzw.leisure.realm.BookRealm;

import io.realm.Realm;

/**
 * author: kang4
 * Date: 2019/12/9
 * Description:
 */
public class RealmHelper {

    private static volatile RealmHelper sInstance;
    private Realm realm;
    private BookRealm book;

    public static RealmHelper getInstance() {
        if (sInstance == null) {
            synchronized (RealmHelper.class) {
                if (sInstance == null) {
                    sInstance = new RealmHelper();
                }
            }
        }
        return sInstance;
    }

    public void setBook(BookRealm book) {
        this.book = book;
    }

    public BookRealm getBook() {
        return book;
    }

    public void init() {
        realm = Realm.getDefaultInstance();
    }

    public Realm getRealm() {
        return realm;
    }

    public void destoryRealm() {
        if (realm != null) {
            realm.close();
        }
    }


}
