package com.softrunapps.rashapplication.data;

import com.google.gson.JsonObject;
import com.softrunapps.rashapplication.model.VideoContentModel;
import com.softrunapps.rashapplication.model.VideoListModel;

import java.util.List;

public interface VideoRepository {
    void getVideoList(JsonObject jsonObject,
                      DataListener<List<VideoListModel.GetContentList>> dataListener);

    void getVideoContent(String acceptLanguage, JsonObject jsonObject,
                      DataListener<VideoContentModel.Result> dataListener);
}
