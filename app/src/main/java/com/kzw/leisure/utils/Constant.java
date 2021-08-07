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

    public static final String DEFAULT_URL = "https://z1.m1907.cn/?jx=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9%E7%AC%AC%E5%9B%9B%E5%AD%A3";
    public static final String URL = "https://z1.m1907.cn/?jx=";
    public static final String KEY_URL = "https://z1.m1907.cn/api/v/";
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

    public static final int ACTIVITY_REQUEST_CODE = 10;
    public static final int ACTIVITY_RESULT_CODE = 20;


    //测试广告ID
    //public static final String FULL_SCREEN_AD_ID = "ca-app-pub-3940256099942544/1033173712";
    //public static final String AWARD_AD_ID = "ca-app-pub-3940256099942544/5354046379";

    //正式广告ID
    public static final String FULL_SCREEN_AD_ID = "ca-app-pub-2169493091779977/7029514724";
    public static final String AWARD_AD_ID = "ca-app-pub-2169493091779977/8131484035";

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
            "\"baseUrl\":\"https://www.230book.com/\"," +
            "\"siteName\":\"顶点小说\"," +
            "\"ruleBookAuthor\":\"id.info@tag.p.0@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"id.intro@tag.p@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"id.info@tag.p.3@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"id.fmimg@tag.img@src\"," +
            "\"ruleChapterList\":\"id.list@tag.li\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"0\"," +
            "\"ruleContentUrl\":\"id.content@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.odd.1@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"\"," +
            "\"ruleSearchLastChapter\":\"class.even@text\"," +
            "\"ruleSearchList\":\"id.nr\"," +
            "\"ruleSearchName\":\"class.odd@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"class.odd@tag.a@href\"," +
            "\"ruleSearchUrl\":\"/modules/article/search.php@searchkey=ruleKeyword&searchtype=articlename&action=login&submit=%26%23160%3B%CB%D1%26%23160%3B%26%23160%3B%CB%F7%26%23160%3B|char=GBK\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"https://www.69shu.org/\"," +
            "\"siteName\":\"69书吧\"," +
            "\"ruleBookAuthor\":\"id.info@tag.small@tag.a@text\"," +
            "\"ruleBookContent\":\"\"," +
            "\"ruleBookInfoInit\":\"class.bookinfo_intro@text\"," +
            "\"ruleBookKind\":\"\"," +
            "\"ruleBookLastChapter\":\"class.update@tag.a@text\"," +
            "\"ruleBookName\":\"id.info@tag.h1@text\"," +
            "\"ruleBookUrlPattern\":\"\"," +
            "\"ruleCoverUrl\":\"class.pic@tag.img@src\"," +
            "\"ruleChapterList\":\"class.chapterlist!0@tag.li!0\"," +
            "\"ruleChapterName\":\"tag.a@text\"," +
            "\"ruleChapterUrl\":\"tag.a@href\"," +
            "\"ruleChapterUrlType\":\"0\"," +
            "\"ruleContentUrl\":\"id.htmlContent@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.odd.1@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"\"," +
            "\"ruleSearchLastChapter\":\"class.even@text\"," +
            "\"ruleSearchList\":\"tag.tr!0\"," +
            "\"ruleSearchName\":\"class.odd@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"class.odd@tag.a@href\"," +
            "\"ruleSearchUrl\":\"modules/article/search.php?searchkey=ruleKeyword&submit=%CB%D1%CB%F7|char=GBK\"" +
            "}," +
            "{" +
            "\"baseUrl\":\"http://www.156n.com/\"," +
            "\"siteName\":\"笔趣阁156\"," +
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
            "\"ruleContentUrl\":\"id.content@tag.p@textNodes\"," +
            "\"ruleContentUrlNext\":\"\"," +
            "\"ruleSearchAuthor\":\"class.s4@text\"," +
            "\"ruleSearchCoverUrl\":\"\"," +
            "\"ruleSearchIntroduce\":\"\"," +
            "\"ruleSearchKind\":\"class.s1@text\"," +
            "\"ruleSearchLastChapter\":\"class.s3@tag.a@text\"," +
            "\"ruleSearchList\":\"class.search-list@tag.li!0\"," +
            "\"ruleSearchName\":\"class.s2@tag.a@text\"," +
            "\"ruleSearchNoteUrl\":\"class.s2@tag.a@href\"," +
            "\"ruleSearchUrl\":\"search.php?keyword=ruleKeyword\"" +
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
            "        \"url\": \"https://z1.m1907.cn/?jx=\",\n" +
            "        \"isHasAd\": \"false\",\n" +
            "        \"siteName\": \"解析\"\n" +
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

    public static final String source="[\n" +
            "  {\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"siteName\": \"小森林导航\",\n" +
            "        \"url\": \"http://www.uziqaq.com/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"电影导航\",\n" +
            "        \"url\": \"https://movie.coolzhanweb.com/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"小森林导航2\",\n" +
            "        \"url\": \"http://www.xsldh.vip/\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"listName\": \"导航\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"siteName\": \"五号站\",\n" +
            "        \"url\": \"https://www.wuhaozhan.net/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"搜片\",\n" +
            "        \"url\": \"http://yun.wudayy.com/\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"listName\": \"聚合收索\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"siteName\": \"全民解析\",\n" +
            "        \"url\": \"https://www.anttechnologys.cn/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"M1907\",\n" +
            "        \"url\": \"https://z1.m1907.cn/?jx=\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"listName\": \"解析\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"siteName\": \"娜菲\",\n" +
            "        \"url\": \"https://www.nfmovies.com/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"芒果影院\",\n" +
            "        \"url\": \"https://www.mguomovie.com/\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"listName\": \"第三方酷站\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"list\": [\n" +
            "      {\n" +
            "        \"siteName\": \"百度\",\n" +
            "        \"url\": \"https://www.baidu.com/\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"siteName\": \"谷歌\",\n" +
            "        \"url\": \"https://www.google.com.hk/\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"listName\": \"搜索\"\n" +
            "  }\n" +
            "]";

}
