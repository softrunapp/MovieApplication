package com.softrunapps.rashapplication.data.network;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.softrunapps.rashapplication.data.DataListener;
import com.softrunapps.rashapplication.data.VideoRepository;
import com.softrunapps.rashapplication.data.network.provider.ApiService;
import com.softrunapps.rashapplication.model.VideoContentModel;
import com.softrunapps.rashapplication.model.VideoListModel;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoRepositoryImpl implements VideoRepository {
    public static final String ERROR_MESSAGE = "خطایی رخ داد مجدد تلاش کنید";
    private final ApiService apiService;

    @Inject
    public VideoRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getVideoList(JsonObject jsonObject,
                             final DataListener<List<VideoListModel.GetContentList>> dataListener) {
        apiService.getVideoList(jsonObject).enqueue(new Callback<VideoListModel>() {
            @Override
            public void onResponse(Call<VideoListModel> call, Response<VideoListModel> response) {
                if (response.isSuccessful()) {
                    dataListener.onResponse(response.body().getResult().getGetContentList());
                } else {
                    try {
                        dataListener.onError(getErrorMessage(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoListModel> call, Throwable t) {
                dataListener.onError(ERROR_MESSAGE);
            }
        });

    }

    @Override
    public void getVideoContent(String acceptLanguage, JsonObject jsonObject,
                                DataListener<VideoContentModel.Result> dataListener) {
        apiService.getVideoContent(acceptLanguage, jsonObject)
                .enqueue(new Callback<VideoContentModel>() {
                    @Override
                    public void onResponse(Call<VideoContentModel> call,
                                           Response<VideoContentModel> response) {
                        if (response.isSuccessful()) {
                            dataListener.onResponse(response.body().getResult());
                        } else {
                            try {
                                dataListener.onError(getErrorMessage(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<VideoContentModel> call, Throwable t) {
                        dataListener.onError(ERROR_MESSAGE);
                    }
                });
    }

    private String getErrorMessage(String errorBody) {
        try {
            JsonObject jsonObject = new Gson().fromJson(errorBody, JsonObject.class);
            return jsonObject.get("message").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR_MESSAGE;
    }
}
