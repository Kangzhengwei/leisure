package com.kzw.leisure.widgets.pageView;

import android.annotation.SuppressLint;

import com.kzw.leisure.bean.Chapter;
import com.kzw.leisure.realm.BookRealm;
import com.kzw.leisure.utils.AppUtils;
import com.kzw.leisure.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络页面加载器
 */

public class PageLoaderNet extends PageLoader {

    private List<String> downloadingChapterList = new ArrayList<>();

    PageLoaderNet(PageView pageView, BookRealm bookRealm, Callback callback) {
        super(pageView, bookRealm, callback);
    }

    @Override
    public void refreshChapterList() {
        if (!callback.getChapterList().isEmpty()) {
            isChapterListPrepare = true;
            // 打开章节
            skipToChapter(book.getDurChapter(), book.getDurChapterPage());
        } else {
         /*   book.setChapterListSize(String.valueOf(catalogs.size()));
            isChapterListPrepare = true;
            // 目录加载完成
            callback.onCategoryFinish(catalogs);
            // 加载并显示当前章节
            skipToChapter(book.getDurChapter(), book.getDurChapterPage());*/
        }
    }


    /*public void changeSourceFinish(Book bookShelfBean) {
        if (bookShelfBean == null) {
            openChapter(catalog.getDurChapterPage());
        } else {
            this.book = bookShelfBean;
            refreshChapterList();
        }
    }*/

    @SuppressLint("DefaultLocale")
    private synchronized void loadContent(final int chapterIndex) {
        if (downloadingChapterList.size() >= 20) return;
        if (chapterIndex >= callback.getChapterList().size() || DownloadingList(listHandle.CHECK, callback.getChapterList().get(chapterIndex).getChapterName()))
            return;
        if (null != book && callback.getChapterList().size() > 0) {

        }
    }

    /**
     * 编辑下载列表
     */
    private synchronized boolean DownloadingList(listHandle editType, String value) {
        if (editType == listHandle.ADD) {
            downloadingChapterList.add(value);
            return true;
        } else if (editType == listHandle.REMOVE) {
            downloadingChapterList.remove(value);
            return true;
        } else {
            return downloadingChapterList.indexOf(value) != -1;
        }
    }

    /**
     * 章节下载完成
     */
    private void finishContent(int chapterIndex) {
        if (chapterIndex == mCurChapterPos) {
            super.parseCurChapter();
        }
        if (chapterIndex == mCurChapterPos - 1) {
            super.parsePrevChapter();
        }
        if (chapterIndex == mCurChapterPos + 1) {
            super.parseNextChapter();
        }
    }

    /**
     * 刷新当前章节
     */
    @SuppressLint("DefaultLocale")
    public void refreshDurChapter() {
        if (callback.getChapterList().isEmpty()) {
            updateChapter();
            return;
        }
        if (callback.getChapterList().size() - 1 < mCurChapterPos) {
            mCurChapterPos = callback.getChapterList().size() - 1;
        }
        skipToChapter(mCurChapterPos, 0);
    }

    @Override
    protected List<String> getChapterContent(Chapter chapter) throws Exception {
        return new ArrayList<>();
    }

    @Override
    protected boolean noChapterData(Chapter chapter) {
        return false;
    }


    private boolean shouldRequestChapter(Integer chapterIndex) {
        return NetworkUtils.isAvailable(AppUtils.getAppContext()) && noChapterData(callback.getChapterList().get(chapterIndex));
    }

    public boolean canParseInt(String str) {
        if (str == null) { //验证是否为空
            return false;
        }
        return str.matches("\\d+"); //使用正则表达式判断该字符串是否为数字，第一个\是转义符，\d+表示匹配1个或 //多个连续数字，"+"和"*"类似，"*"表示0个或多个
    }

    // 装载上一章节的内容
    @Override
    void parsePrevChapter() {
        if (mCurChapterPos >= 1) {
            loadContent(mCurChapterPos - 1);
        }
        super.parsePrevChapter();
    }

    // 装载当前章内容。
    @Override
    void parseCurChapter() {
        for (int i = mCurChapterPos; i < Math.min(mCurChapterPos + 5, book.getChapterListSize()); i++) {
            loadContent(i);
        }
        super.parseCurChapter();
    }


    // 装载下一章节的内容
    @Override
    void parseNextChapter() {
        for (int i = mCurChapterPos; i < Math.min(mCurChapterPos + 5, book.getChapterListSize()); i++) {
            loadContent(i);
        }
        super.parseNextChapter();
    }

    @Override
    public void updateChapter() {
        // mPageView.getActivity().toast("目录更新中");

    }

    @Override
    public void closeBook() {
        super.closeBook();
    }

    public enum listHandle {
        ADD, REMOVE, CHECK
    }

}
