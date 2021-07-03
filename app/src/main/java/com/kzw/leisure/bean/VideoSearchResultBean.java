package com.kzw.leisure.bean;

import java.io.Serializable;
import java.util.List;

public class VideoSearchResultBean implements Serializable {


    /**
     * type : tv
     * data : [{"name":"斗破苍穹第四季","year":"2021","source":{"eps":[{"name":"第01集","url":"https://vod.bunediy.com/20210328/yzhmPJjo/index.m3u8"},{"name":"第02集","url":"https://vod.bunediy.com/20210328/ZUJJw6Cb/index.m3u8"},{"name":"第03集","url":"https://vod.bunediy.com/20210404/MhfaZ0xK/index.m3u8"},{"name":"第04集","url":"https://vod.bunediy.com/20210411/Onbisbqf/index.m3u8"},{"name":"第05集","url":"https://vod.bunediy.com/20210418/IfFc7g5C/index.m3u8"}]}},{"name":"斗破苍穹第四季","year":"2023","source":{"eps":[{"name":"预告","url":"https://d.mhqiyi.com/20210111/AtnitneO/index.m3u8"}]}}]
     * ep : 1
     * y : [{"tType":"show","showName":"饿了么红包 天天可领！平均领10元！","showType":"text","clickType":"link","clickContent":"https://t0ay8.m1907.cn/ele/","clickId":"busds6g6kkmte8lsjhm0"}]
     * sp : 0
     */

    private String type;
    private String ep;
    private int sp;
    private List<DataBean> data;
    private List<YBean> y;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEp() {
        return ep;
    }

    public void setEp(String ep) {
        this.ep = ep;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<YBean> getY() {
        return y;
    }

    public void setY(List<YBean> y) {
        this.y = y;
    }

    public static class DataBean {
        /**
         * name : 斗破苍穹第四季
         * year : 2021
         * source : {"eps":[{"name":"第01集","url":"https://vod.bunediy.com/20210328/yzhmPJjo/index.m3u8"},{"name":"第02集","url":"https://vod.bunediy.com/20210328/ZUJJw6Cb/index.m3u8"},{"name":"第03集","url":"https://vod.bunediy.com/20210404/MhfaZ0xK/index.m3u8"},{"name":"第04集","url":"https://vod.bunediy.com/20210411/Onbisbqf/index.m3u8"},{"name":"第05集","url":"https://vod.bunediy.com/20210418/IfFc7g5C/index.m3u8"}]}
         */

        private String name;
        private String year;
        private SourceBean source;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public static class SourceBean {
            private List<EpsBean> eps;

            public List<EpsBean> getEps() {
                return eps;
            }

            public void setEps(List<EpsBean> eps) {
                this.eps = eps;
            }

            public static class EpsBean {
                /**
                 * name : 第01集
                 * url : https://vod.bunediy.com/20210328/yzhmPJjo/index.m3u8
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }

    public static class YBean {
        /**
         * tType : show
         * showName : 饿了么红包 天天可领！平均领10元！
         * showType : text
         * clickType : link
         * clickContent : https://t0ay8.m1907.cn/ele/
         * clickId : busds6g6kkmte8lsjhm0
         */

        private String tType;
        private String showName;
        private String showType;
        private String clickType;
        private String clickContent;
        private String clickId;

        public String getTType() {
            return tType;
        }

        public void setTType(String tType) {
            this.tType = tType;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getShowType() {
            return showType;
        }

        public void setShowType(String showType) {
            this.showType = showType;
        }

        public String getClickType() {
            return clickType;
        }

        public void setClickType(String clickType) {
            this.clickType = clickType;
        }

        public String getClickContent() {
            return clickContent;
        }

        public void setClickContent(String clickContent) {
            this.clickContent = clickContent;
        }

        public String getClickId() {
            return clickId;
        }

        public void setClickId(String clickId) {
            this.clickId = clickId;
        }
    }
}
