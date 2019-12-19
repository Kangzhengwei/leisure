package com.kzw.leisure.utils;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by Android on 2019/2/26.
 */

public class Constant {

    public static final String DEFAULT_URL = "http://www.2w2w.tv/";
    public static final String FRONT_DOWNLOAD_URL = "https://www.jianguoyun.com/p/DXyqo6IQ0Zb6Bxj2ibEC";
    public static final Pattern putPattern = Pattern.compile("@put:(\\{[^}]+?\\})", Pattern.CASE_INSENSITIVE);
    public static final Pattern getPattern = Pattern.compile("@get:\\{([^}]+?)\\}", Pattern.CASE_INSENSITIVE);
    public static final Pattern headerPattern = Pattern.compile("@Header:\\{.+?\\}", Pattern.CASE_INSENSITIVE);
    public static final Pattern JS_PATTERN = Pattern.compile("(<js>[\\w\\W]*?</js>|@js:[\\w\\W]*$)", Pattern.CASE_INSENSITIVE);
    public static Type MAP_STRING = new TypeToken<Map<String, String>>() {
    }.getType();

    public static final Pattern EXP_PATTERN = Pattern.compile("\\{\\{([\\w\\W]*?)\\}\\}");
    public static final ScriptEngine SCRIPT_ENGINE = new ScriptEngineManager().getEngineByName("rhino");

    public static final Pattern pagePattern = Pattern.compile("\\{(.*?)\\}");


    public static final String bookRuleSource = "[" +
            "{" +
            "\"baseUrl\":\"https://www.biquge.biz/\"," +
            "\"siteName\":\"笔趣阁biz\"," +
            "\"ruleBookAuthor\":\"id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"id.info@tag.p.3@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.result-game-item-info-tag.0@tag.span.1@text\"," +
            "\"ruleSearchCoverUrl\":\"class.result-game-item-pic@tag.img@src\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.result-game-item-info-tag.1@tag.span.1@text\"," +
            "\"ruleSearchLastChapter\":\"class.result-game-item-info-tag.3@tag.a@text\"," +
            "\"ruleSearchList\":\"class.result-item result-game-item\"," +
            "\"ruleSearchName\":\"class.result-item-title result-game-item-title.@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"class.result-item-title result-game-item-title.@tag.a@href\"," +
            "\"ruleSearchUrl\":\"search.php?keyword=ruleKeyword|UTF-8\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.xinxs.la/\"," +
            "\"siteName\":\"笔趣阁\"," +
            "\"ruleBookAuthor\":\"id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"id.info@tag.p.3@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd!0:1:2:3:4:5:6:7:8:9:10:11:12\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.s4@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.s1@text\"," +
            "\"ruleSearchLastChapter\":\"class.s3@text\"," +
            "\"ruleSearchList\":\"class.search-list@tag.li!0\"," +
            "\"ruleSearchName\":\"class.s2@text\"," +
            "\"ruleSearchNoteUrl\":\"class.s2@tag.a@href\"," +
            "\"ruleSearchUrl\":\"https://sou.xanbhx.com/search?siteid=xsla&q=ruleKeyword|UTF-8\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.xbiquge6.com\"," +
            "\"siteName\":\"新笔趣阁\"," +
            "\"ruleBookAuthor\":\"id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"id.info@tag.p.3@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd\"," +
            "\"ruleChapterName\":\"tag.a.0@text\"," +
            "\"ruleChapterUrl\":\"tag.a.0@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.result-game-item-info-tag.0@tag.span.1@text\"," +
            "\"ruleSearchCoverUrl\":\"tag.img.0@src\"," +
            "\"ruleSearchIntroduce\":\"id.intro@tag.p@textNodes\"," +
            "\"ruleSearchKind\":\"class.result-game-item-info-tag.1@tag.span.1@text\"," +
            "\"ruleSearchLastChapter\":\"class.result-game-item-info-tag.3@tag.a.0@text\"," +
            "\"ruleSearchList\":\"class.result-item\"," +
            "\"ruleSearchName\":\"class.result-item-title.0@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"class.result-item-title.0@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"https://www.xbiquge6.com/search.php?keyword=ruleKeyword\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.xbaquge.com\"," +
            "\"siteName\":\"新笔趣阁2\"," +
            "\"ruleBookAuthor\":\"id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@tag.p@textNodes\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"id.info@tag.p.3@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a.0@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.result-game-item-info-tag.0@tag.span.1@text\"," +
            "\"ruleSearchCoverUrl\":\"tag.img.0@src\"," +
            "\"ruleSearchIntroduce\":\"id.intro@tag.p@textNodes\"," +
            "\"ruleSearchKind\":\"class.result-game-item-info-tag.1@tag.span.1@text\"," +
            "\"ruleSearchLastChapter\":\"class.result-game-item-info-tag.3@tag.a.0@text\"," +
            "\"ruleSearchList\":\"class.result-item\"," +
            "\"ruleSearchName\":\"class.result-item-title.0@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"class.result-item-title.0@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"https://www.xbaquge.com/search.php?q=ruleKeyword\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.xs222.tw\"," +
            "\"siteName\":\"顶点小说\"," +
            "\"ruleBookAuthor\":\"class.box_con.0@id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"class.synopsisArea_detail@tag.p.3@text\"," +
            "\"ruleBookName\":\"class.box_con.0@id.info@tag.h1.0@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img.0@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.result-game-item-info-tag.0@tag.span.1@text\"," +
            "\"ruleSearchCoverUrl\":\"tag.img.0@src\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.result-game-item-info-tag.1@tag.span.1@text\"," +
            "\"ruleSearchLastChapter\":\"class.result-game-item-info-tag.3@tag.a.0@text\"," +
            "\"ruleSearchList\":\"class.result-item\"," +
            "\"ruleSearchName\":\"class.result-item-title.0@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"class.result-item-title.0@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"https://www.xs222.tw/search.php?q=ruleKeyword\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.dingdiann.com\"," +
            "\"siteName\":\"顶点小说2\"," +
            "\"ruleBookAuthor\":\"class.info@tag.p.0@text#作者：\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@textNodes\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"\"," +
            "\"ruleBookName\":\"class.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd!0:1:2:3:4:5:6:7:8:9:10:11\"," +
            "\"ruleChapterName\":\"tag.a.0@text\"," +
            "\"ruleChapterUrl\":\"tag.a.0@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.s4.0@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.s1.0@text#.*\\\\[|\\\\]\"," +
            "\"ruleSearchLastChapter\":\"class.s3.0@tag.a@text\"," +
            "\"ruleSearchList\":\"class.novelslist2@tag.li!0\"," +
            "\"ruleSearchName\":\"class.s2.0@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"class.s2.0@tag.a@href\"," +
            "\"ruleSearchUrl\":\"https://www.dingdiann.com/searchbook.php?keyword=ruleKeyword\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.mht.tw\"," +
            "\"siteName\":\"棉花糖\"," +
            "\"ruleBookAuthor\":\"\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"\"," +
            "\"ruleBookName\":\"\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img.0@src\"," +
            "\"ruleChapterList\":\"id.list@tag.dd!0:1:2:3:4:5:6:7:8\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"tag.span.3@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"tag.span.0@text#\\\\[|\\\\]\"," +
            "\"ruleSearchLastChapter\":\"tag.span.2@tag.a@text\"," +
            "\"ruleSearchList\":\"id.newscontent@tag.ul.0@tag.li\"," +
            "\"ruleSearchName\":\"tag.span.1@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.span.1@tag.a@href\"," +
            "\"ruleSearchUrl\":\"/case.php?m=search@key=ruleKeyword\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.daocaorenshuwu.com\"," +
            "\"siteName\":\"稻草人书屋\"," +
            "\"ruleBookAuthor\":\"class.col-md-4 col-sm-6 dark.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"class.book-detail@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"class.col-md-8 col-sm-6 dark@text\"," +
            "\"ruleBookName\":\"class.book-name@tag.a@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"class.book-img-middel@src\"," +
            "\"ruleChapterList\":\"class.col-md-6 item\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.cont-text@tag.p@text\"," +
            "\"ruleContentUrlNext\":\"@css:ul>li.active+li>a@href\\n<js>\\nn=result;\\nx=n.search(\\\"_\\\");\\nif (x==-1){result=null};\\nif (x>-1){result=n}\\n</js>\"," +
            "\"ruleSearchAuthor\":\"tag.td.1@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"\"," +
            "\"ruleSearchLastChapter\":\"\"," +
            "\"ruleSearchList\":\"class.table-condensed@tag.tr!0\"," +
            "\"ruleSearchName\":\"tag.td.0@tag.a@title\"," +
            "\"ruleSearchNoteUrl\":\"tag.td.0@tag.a@href\"," +
            "\"ruleSearchUrl\":\"/plus/search.php?keyword=ruleKeyword&PageNo=searchPage\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.bhzw.cc/\"," +
            "\"siteName\":\"冰火中文网\"," +
            "\"ruleBookAuthor\":\"class.zuozhe@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"class.intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"class.name@tag.a@text\"," +
            "\"ruleBookName\":\"class.page-title page-title1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"class.pic@tag.img@src\"," +
            "\"ruleChapterList\":\"class.float-list fill-block@tag.li\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"0\"," +
            "\"ruleContentUrl\":\"id.ChapterContents@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"tag.td.3@tag.a@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"tag.td.1@text\"," +
            "\"ruleSearchLastChapter\":\"tag.td.2@tag.a.1@text\"," +
            "\"ruleSearchList\":\"class.bd@tag.tbody@tag.tr\"," +
            "\"ruleSearchName\":\"tag.td.2@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.td.2.@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"modules/article/search.php@searchkey=ruleKeyword|char=gbk\"" +
            "}" +
            "]\n";
    public static final String ruleSource = "[" +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m\\u003dvod-search\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"class.suf@text\"," +
            "\"ruleTypeList\":\"class.suf\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"酷云资源网\"," +
            "\"videoSourceUrl\":\"https://www.okzy.co\"" +
            "}," +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m=vod-search\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"h3@text\"," +
            "\"ruleTypeList\":\"h3\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"209资源网\"," +
            "\"videoSourceUrl\":\"http://www.156zy.me\"" +
            "}," +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m=vod-search\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"h3@text\"," +
            "\"ruleTypeList\":\"h3\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"135资源网\"," +
            "\"videoSourceUrl\":\"http://135zy0.com\"" +
            "}," +
            "{" +
            "\"params\":{\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"1\"," +
            "\"ruleSearchList\":\"class.DianDian@tag.td.0\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m=vod-search\"," +
            "\"ruleSeriesList\":\"class.movievod@tag.ul\"," +
            "\"ruleItem\":\"tag.li@tag.a\"," +
            "\"ruleSeriesName\":\"tag.a@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"li@text\"," +
            "\"ruleTypeList\":\"class.movievod@tag.li\"," +
            "\"ruleVideoName\":\"class.videoDetail@tag.li.0@text\"," +
            "\"ruleVideoImage\":\"class.videoPic@tag.img@src\"," +
            "\"siteName\":\"永久资源网\"," +
            "\"videoSourceUrl\":\"http://www.yongjiuzy2.com\"" +
            "}," +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m=vod-search\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"h3@text\"," +
            "\"ruleTypeList\":\"h3\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"酷哈资源网\"," +
            "\"videoSourceUrl\":\"http://www.666zy.com\"" +
            "}," +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"/index.php?m\\u003dvod-search\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"class.suf@text\"," +
            "\"ruleTypeList\":\"class.suf\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"最大资源网\"," +
            "\"videoSourceUrl\":\"http://www.zuidazy3.com\"" +
            "}" +
            "]\n";
    //永久资源网备用地址http://www.yongjiuzy.cc

    /*
    public static final String webSite = "[\n" +
            "    {\n" +
            "        \"url\": \"http://www.81ju.cn/ \",\n" +
            "        \"siteName\": \"扒一扒\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.2w2w.tv/ \",\n" +
            "        \"siteName\": \"2w\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.baidu.com/ \",\n" +
            "        \"siteName\": \"百度\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.dyrizhi.com\",\n" +
            "        \"siteName\": \"电影日志\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.xn--6o0a480a.cn \",\n" +
            "        \"siteName\": \"维赞影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.pilipali.cc \",\n" +
            "        \"siteName\": \"噼里啪啦\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.28dm.com \",\n" +
            "        \"siteName\": \"新世界动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.yxdm.tv\",\n" +
            "        \"siteName\": \"逸轩动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.qiqidongman.com\",\n" +
            "        \"siteName\": \"奇奇动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.ikanfan.com\",\n" +
            "        \"siteName\": \"爱看藩\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.vodxc.com\",\n" +
            "        \"siteName\": \"星辰影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.yeziyy.com\",\n" +
            "        \"siteName\": \"椰子影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.bimibimi.cc\",\n" +
            "        \"siteName\": \"笔迷笔迷\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.fengchedm.com\",\n" +
            "        \"siteName\": \"风车动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.migudm.cn\",\n" +
            "        \"siteName\": \"咪咕\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.kdy666.com\",\n" +
            "        \"siteName\": \"看电影网\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.jiadiandm.com\",\n" +
            "        \"siteName\": \"好看影视\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://dz.zhaifulifabu.com:9527/ \",\n" +
            "        \"siteName\": \"备用地址\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.66s.cc/ \",\n" +
            "        \"siteName\": \"6v电影\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.4kdy.net/ \",\n" +
            "        \"siteName\": \"4k电影\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.y80s.net/ \",\n" +
            "        \"siteName\": \"80s\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://1.61ww16.com \",\n" +
            "        \"siteName\": \"趣淘视频\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.kkkkmao.com \",\n" +
            "        \"siteName\": \"4k屋\"\n" +
            "    }\n" +
            "]";
*/


    /*
    public static final String webSite = "[\n" +
            "    {\n" +
            "        \"url\": \"https://www.baidu.com/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"百度\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.2w2w.tv/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"ok资源网\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.uziqaq.com/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"小森林导航\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.xsldh.vip/ \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"小森林导航2\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.cuteforest.com/nav.html\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"小森林导航3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.dyrizhi.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"电影日志\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.xn--6o0a480a.cn \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"维赞影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.4kdy.net/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"4k电影\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.pilipali.cc \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"噼里啪啦\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.28dm.com \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"新世界动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.yxdm.tv\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"逸轩动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.qiqidongman.com\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"奇奇动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.ikanfan.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"爱看藩\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.vodxc.com\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"星辰影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.yeziyy.com\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"椰子影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.bimibimi.cc\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"笔迷笔迷\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.fengchedm.com\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"风车动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.migudm.cn\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"咪咕\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.kdy666.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"看电影网\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.jiadiandm.com\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"好看影视\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.66s.cc/ \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"6v电影\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.y80s.net/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"80s\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://1.61ww16.com \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"趣淘视频\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.kkkkmao.com \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"4k屋\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.xyavi.com/\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"百灵鸟影视\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.hao5.net/\",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"电视直播\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.81ju.cn/ \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"扒一扒\"\n" +
            "    }\n" +
            "]";

*/
//http://www.goudaitv.com/
    public static final String webSite = "[\n" +
            "    {\n" +
            "        \"url\": \"https://www.baidu.com/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"百度\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.2w2w.tv/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"ok资源网\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.uziqaq.com/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"小森林导航\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.xsldh.vip/ \",\n" +
            "        \"isHasAd\": \"true\",\n" +
            "        \"siteName\": \"小森林导航2\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.cuteforest.com/nav.html\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"小森林导航3\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.dyrizhi.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"电影日志\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.xn--6o0a480a.cn \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"维赞影院\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.4kdy.net/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"4k电影\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.pilipali.cc \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"噼里啪啦\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.28dm.com \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"新世界动漫\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.ikanfan.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"爱看藩\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.migudm.cn\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"咪咕\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"http://www.kdy666.com\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"看电影网\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"url\": \"https://www.y80s.net/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"80s\"\n" +
            "    }\n" +
            "]";

}
