package com.softrunapps.rashapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoListModel {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private Result result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public class GetContentList {

        @SerializedName("ContentID")
        @Expose
        private Integer contentID;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Summary")
        @Expose
        private String summary;
        @SerializedName("ThumbImage")
        @Expose
        private String thumbImage;
        @SerializedName("ZoneID")
        @Expose
        private Integer zoneID;
        @SerializedName("FavoriteStatus")
        @Expose
        private Boolean favoriteStatus;

        public Integer getContentID() {
            return contentID;
        }

        public void setContentID(Integer contentID) {
            this.contentID = contentID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbImage() {
            return thumbImage;
        }

        public void setThumbImage(String thumbImage) {
            this.thumbImage = thumbImage;
        }

        public Integer getZoneID() {
            return zoneID;
        }

        public void setZoneID(Integer zoneID) {
            this.zoneID = zoneID;
        }

        public Boolean getFavoriteStatus() {
            return favoriteStatus;
        }

        public void setFavoriteStatus(Boolean favoriteStatus) {
            this.favoriteStatus = favoriteStatus;
        }

        public boolean isSeries() {
            return this.zoneID == 3;
        }

        public boolean isMovie() {
            return this.zoneID == 4;
        }

        public String getVideoType() {
            if (isMovie())
                return "سینمایی";
            else if (isSeries())
                return "سریال";
            else
                return "نامشخص";
        }

    }

    public class Result {

        @SerializedName("GetContentList")
        @Expose
        private List<GetContentList> getContentList = null;

        public List<GetContentList> getGetContentList() {
            return getContentList;
        }

        public void setGetContentList(List<GetContentList> getContentList) {
            this.getContentList = getContentList;
        }
    }
}
