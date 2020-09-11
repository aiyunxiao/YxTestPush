package com.yunxiao.yxtestpush;

import java.io.Serializable;

public class ExtrasBean implements Serializable {
    /**
     * type : 8
     * isApi : true
     * examId : 222
     * courseId : 1234
     * adType : 4
     * clientType : 1
     * deviceType : 1
     * url : "http://www.baidu.com"
     * adId : 131234567886543234567
     */

    private String type="";
    //表示 是否是从后台API发送的通知
    private boolean isApi;
    private String examId="";
    private String courseId="";
    //后台返回的广告类型
    private int adType;
    private String url="";
    //后台返回的广告id（为了匹配到对应的广告）
    private String adId="";
    private String target="";
    private DataBean data=new DataBean();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsApi() {
        return isApi;
    }

    public void setIsApi(boolean isApi) {
        this.isApi = isApi;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String mUrl) {
        url = mUrl;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String mAdId) {
        adId = mAdId;
    }


    public String getTarget() {
        return target;
    }

    public void setTarget(String mTarget) {
        target = mTarget;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean mData) {
        data = mData;
    }

    @Override
    public String toString() {
        return "ExtrasBean{" +
                "type='" + type + '\'' +
                ", isApi=" + isApi +
                ", examId='" + examId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", adType=" + adType +
                ", url='" + url + '\'' +
                ", adId='" + adId + '\'' +
                ", target='" + target + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable {
        private String type="";
        private String page="";
        private String courseId="";
        private String examId="";
        private int adType;
        private String adId="";
        private String homeworkId="";

        public String getType() {
            return type;
        }

        public void setType(String mType) {
            type = mType;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String mPage) {
            page = mPage;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int mAdType) {
            adType = mAdType;
        }

        public String getAdId() {
            return adId;
        }

        public void setAdId(String mAdId) {
            adId = mAdId;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String mCourseId) {
            courseId = mCourseId;
        }

        public String getExamId() {
            return examId;
        }

        public void setExamId(String mExamId) {
            examId = mExamId;
        }

        public String getHomeworkId() {
            return homeworkId;
        }

        public void setHomeworkId(String mHomeworkId) {
            homeworkId = mHomeworkId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "type='" + type + '\'' +
                    ", page='" + page + '\'' +
                    ", courseId='" + courseId + '\'' +
                    ", examId='" + examId + '\'' +
                    ", adType=" + adType +
                    ", adId='" + adId + '\'' +
                    ", homeworkId='" + homeworkId + '\'' +
                    '}';
        }
    }
}