package com.softrunapps.rashapplication.storage.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "videos")
public class Video {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private Integer contentID;
    private String title;
    private String summary;
    private String thumbImage;
    private Integer zoneID;
    private Boolean favoriteStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(contentID, video.contentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentID);
    }
}
