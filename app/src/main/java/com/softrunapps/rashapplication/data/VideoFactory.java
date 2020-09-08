package com.softrunapps.rashapplication.data;

import com.softrunapps.rashapplication.data.network.VideoRepositoryImpl;
import com.softrunapps.rashapplication.data.network.provider.ApiService;

import javax.inject.Inject;

public class VideoFactory {
    private final ApiService apiService;

    @Inject
    public VideoFactory(ApiService apiService) {
        this.apiService = apiService;
    }

    public VideoRepository createVideoFactory() {
        return new VideoRepositoryImpl(apiService);
    }
}
