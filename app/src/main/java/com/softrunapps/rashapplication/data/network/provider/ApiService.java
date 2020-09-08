package com.softrunapps.rashapplication.data.network.provider;

import com.google.gson.JsonObject;
import com.softrunapps.rashapplication.model.VideoContentModel;
import com.softrunapps.rashapplication.model.VideoListModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("GetContentList")
    Call<VideoListModel> getVideoList(@Body JsonObject body);

    @POST("GetContent")
    Call<VideoContentModel> getVideoContent(
            @Header("Accept-Language") String acceptLanguage,
            @Body JsonObject body);
}

