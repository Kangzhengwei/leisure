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
    //public static final String DEFAULT_URL = "http://www.yongjiuzy.cc";
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
            "\"ruleSearchAuthor\":\"tag.td.3@tag.a@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"tag.td.1@text\"," +
            "\"ruleSearchLastChapter\":\"tag.td.2@tag.a.1@text\"," +
            "\"ruleSearchList\":\"class.bd@tag.tbody@tag.tr\"," +
            "\"ruleSearchName\":\"tag.td.2@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.td.2.@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"modules/article/search.php@searchkey=ruleKeyword|char=gbk\"" +
            "}," +
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
            "\"ruleChapterList\":\"id.list@tag.dd!0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20:21:22:23:24:25:26:27:28:29:30:31:32:33:34:35:36:37:38:39\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"1\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleSearchAuthor\":\"class.s4@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.s1@text\"," +
            "\"ruleSearchLastChapter\":\"class.s3@text\"," +
            "\"ruleSearchList\":\"class.search-list@tag.li!0\"," +
            "\"ruleSearchName\":\"class.s2@text\"," +
            "\"ruleSearchNoteUrl\":\"class.s2@tag.a@href\"," +
            "\"ruleSearchUrl\":\"https://sou.xanbhx.com/search?siteid=xsla&q=ruleKeyword|UTF-8\"" +
            "}" +
            "]\n";
    public static final String ruleSource = "[" +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
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
            "\"params\":{\"submit\":\"\",\"wd\":\"searchKey\"}," +
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
            "\"videoSourceUrl\":\"http://www.yongjiuzy1.com\"" +
            "}," +
            "{" +
            "\"params\":{\"submit\":\"search\",\"wd\":\"searchKey\"}," +
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
            "\"videoSourceUrl\":\"http://www.zuidazy1.com\"" +
            "}" +
            "]\n";

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
            "        \"url\": \"http://www.zybird.com\",\n" +
            "        \"siteName\": \"综艺鸟\"\n" +
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
            "        \"url\": \"http://www.meijuhui.net\",\n" +
            "        \"siteName\": \"美剧汇\"\n" +
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


}
