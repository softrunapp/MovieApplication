package com.softrunapps.rashapplication.view.main.ui.video_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softrunapps.rashapplication.storage.VideoLocalRepository;
import com.softrunapps.rashapplication.storage.room.Video;

import java.util.List;

public class VideoListViewModel extends AndroidViewModel {
    private VideoLocalRepository videoLocalRepository;

    public VideoListViewModel(@NonNull Application application) {
        super(application);
        videoLocalRepository = new VideoLocalRepository(application);
    }

    public LiveData<List<Video>> getAll() {
        return videoLocalRepository.getAll();
    }
}