package com.softrunapps.rashapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoContentModel {

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


    public class Result {
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Summary")
        @Expose
        private String summary;
        @SerializedName("LandscapeImage")
        @Expose
        private String landscapeImage;
        @SerializedName("FavoriteStatus")
        @Expose
        private Boolean favoriteStatus;

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

        public String getLandscapeImage() {
            return landscapeImage;
        }

        public void setLandscapeImage(String landscapeImage) {
            this.landscapeImage = landscapeImage;
        }

        public Boolean getFavoriteStatus() {
            return favoriteStatus;
        }

        public void setFavoriteStatus(Boolean favoriteStatus) {
            this.favoriteStatus = favoriteStatus;
        }
    }
}
