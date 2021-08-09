package com.kzw.leisure.bean;

import java.io.Serializable;
import java.util.List;

public class SiteSourceBean implements Serializable {

    private List<ListDTO> list;
    private String listName;

    public List<ListDTO> getList() {
        return list;
    }

    public void setList(List<ListDTO> list) {
        this.list = list;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public static class ListDTO {
        private String siteName;
        private String url;

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ListDTO{" +
                    "siteName='" + siteName + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SiteSourceBean{" +
                "list=" + list +
                ", listName='" + listName + '\'' +
                '}';
    }
}
