package com.kzw.leisure.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: kang4
 * Date: 2019/12/2
 * Description:
 */
public class VideoBean implements Serializable {

    private String videoName;
    private String videoImage;
    private List<Series> list;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public List<Series> getList() {
        return list;
    }

    public void setList(List<Series> list) {
        this.list = list;
    }

    public static class Series {
        private String urlType;
        private List<Url> list;

        public String getUrlType() {
            return urlType;
        }

        public void setUrlType(String urlType) {
            this.urlType = urlType;
        }

        public List<Url> getList() {
            return list;
        }

        public void setList(List<Url> list) {
            this.list = list;
        }

        public static class Url {
            private String videoSeries;
            private String videoUrl;

            public String getVideoSeries() {
                return videoSeries;
            }

            public void setVideoSeries(String videoSeries) {
                this.videoSeries = videoSeries;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
