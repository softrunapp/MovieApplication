package com.softrunapps.rashapplication.utils;

import com.google.gson.JsonObject;
import com.softrunapps.rashapplication.model.VideoListModel;
import com.softrunapps.rashapplication.storage.room.Video;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Video> convertDataVideoToVideoList(
            List<VideoListModel.GetContentList> dataVideoList) {
        List<Video> list = new ArrayList<>();
        for (VideoListModel.GetContentList getContentList : dataVideoList) {
            Video video = new Video();
            video.setContentID(getContentList.getContentID());
            video.setTitle(getContentList.getTitle());
            video.setSummary(getContentList.getSummary());
            video.setThumbImage(getContentList.getThumbImage());
            video.setZoneID(getContentList.getZoneID());
            video.setFavoriteStatus(getContentList.getFavoriteStatus());
            list.add(video);
        }
        return list;
    }

    public static JsonObject getRequestJsonObject(int page) {
        JsonObject propertiesJsonObject = new JsonObject();
        propertiesJsonObject.addProperty("RequestType", 2);
        propertiesJsonObject.addProperty("PageSize", 10);
        propertiesJsonObject.addProperty("PageIndex", page);
        propertiesJsonObject.addProperty("OrderBy", "createdate");
        propertiesJsonObject.addProperty("Order", "desc");
        JsonObject requestJsonObject = new JsonObject();
        requestJsonObject.add("request", propertiesJsonObject);
        return requestJsonObject;
    }

    public static String getHtmlStyledText(String content) {
        return "<HTML><HEAD><LINK href=\"style.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD>" +
                "<body><div class=\"text\">" +
                content +
                "</div></body></HTML>";
    }
}
