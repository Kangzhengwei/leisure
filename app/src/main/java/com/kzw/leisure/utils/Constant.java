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

    public static final String DEFAULT_URL = "https://www.baidu.com/";
    public static final String URL = "https://z1.m1907.cn/?jx=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E7%AC%AC%E5%9B%9B%E5%AD%A3";
    public static final String KWYURL = "https://z1.m1907.cn/api/v/";
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

    public static String QUERY_SEARCH = "search_prompt?k=KEYWORD";
    public static String QUERY_VIDEO = "v/?z=TOKEN&jx=KEYWORD";
    public static String QUERY_BASE = "https://z1.m1907.cn/api/";


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
            "\"ruleSearchUrl\":\"search.php?q=ruleKeyword|UTF-8\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.xxbiquge.net/\"," +
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
            "\"ruleSearchCoverUrl\":\"class.result-game-item-pic@tag.img@src\"," +
            "\"ruleSearchIntroduce\":\"id.intro@tag.p@textNodes\"," +
            "\"ruleSearchKind\":\"class.result-game-item-info-tag.1@tag.span.1@text\"," +
            "\"ruleSearchLastChapter\":\"class.result-game-item-info-tag.3@tag.a.0@text\"," +
            "\"ruleSearchList\":\"class.result-item\"," +
            "\"ruleSearchName\":\"class.result-item-title.0@tag.a.0@text\"," +
            "\"ruleSearchNoteUrl\":\"class.result-item-title.0@tag.a.0@href\"," +
            "\"ruleSearchUrl\":\"search.php?keyword=ruleKeyword\"" +
            "}" +
            "]\n";

   /* public static final String ruleSource = "[" +
            "{" +
            "\"sourceType\":\"1\"," +
            "\"ruleSearchList\":\"class.row\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"search.asp@searchword=ruleKeyword|char=gbk\"," +
            "\"ruleSeriesList\":\"tag.table.1@tag.a!0:1\"," +
            "\"ruleItem\":\"tag.a\"," +
            "\"ruleSeriesName\":\"tag.a@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"h1@text\"," +
            "\"ruleTypeList\":\"h1\"," +
            "\"ruleVideoName\":\"tag.table.2@td.0@text\"," +
            "\"ruleVideoImage\":\"class.img@tag.img@src\"," +
            "\"siteName\":\"酷云资源网\"," +
            "\"videoSourceUrl\":\"http://www.kuyunzy1.com/\"" +
            "}" +
            "]\n";*/

    public static final String ruleSource = "[" +
            "{" +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"index.php?m=vod-search@submit=search&wd=ruleKeyword\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"h3@text\"," +
            "\"ruleTypeList\":\"h3\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"605资源\"," +
            "\"videoSourceUrl\":\"http://605zy.co/\"" +
            "}," +
            "{" +
            "\"sourceType\":\"0\"," +
            "\"ruleSearchList\":\"class.xing_vb4@tag.a\"," +
            "\"ruleSearchName\":\"tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"tag.a@href\"," +
            "\"ruleSearchUrl\":\"index.php?m\\u003dvod-search@submit=search&wd=ruleKeyword\"," +
            "\"ruleSeriesList\":\"class.vodplayinfo@tag.ul\"," +
            "\"ruleItem\":\"li\"," +
            "\"ruleSeriesName\":\"li@text\"," +
            "\"ruleSeriesNoteUrl\":\"  \"," +
            "\"rulePlayType\":\"class.suf@text\"," +
            "\"ruleTypeList\":\"class.suf\"," +
            "\"ruleVideoName\":\"class.vodh@tag.h2@text\"," +
            "\"ruleVideoImage\":\"class.vodImg@tag.img@src\"," +
            "\"siteName\":\"百度云资源\"," +
            "\"videoSourceUrl\":\"http://hct.dbyunzy.com/\"" +
            "}" +
            "]\n";

    public static final String webSite = "[\n" +
            "    {\n" +
            "        \"url\": \"https://www.baidu.com/ \",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"百度\"\n" +
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
            "    }\n" +
            "]";

}
