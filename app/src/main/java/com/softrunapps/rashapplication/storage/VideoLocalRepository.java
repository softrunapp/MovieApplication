package com.softrunapps.rashapplication.storage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.softrunapps.rashapplication.storage.room.AppDatabase;
import com.softrunapps.rashapplication.storage.room.Video;
import com.softrunapps.rashapplication.storage.room.VideoDao;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class VideoLocalRepository implements VideoDao {
    private VideoDao videoDao;
    private LiveData<List<Video>> allVideos;

    @Inject
    public VideoLocalRepository(Application application) {
        videoDao = AppDatabase.getInstance(application).videoDao();
        allVideos = videoDao.getAll();
    }

    @Override
    public void insert(List<Video> videos) {
        new InsertAsyncTask(videoDao).execute(videos.toArray(new Video[videos.size()]));
    }

    @Override
    public void deleteAll() {
        new DeleteAsyncTask(videoDao).execute();
    }

    @Override
    public LiveData<List<Video>> getAll() {
        return allVideos;
    }

    private static class InsertAsyncTask extends AsyncTask<Video, Void, Void> {
        private VideoDao videoDao;

        public InsertAsyncTask(VideoDao videoDao) {
            this.videoDao = videoDao;
        }

        @Override
        protected Void doInBackground(Video... videos) {
            videoDao.insert(Arrays.asList(videos));
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideoDao videoDao;

        public DeleteAsyncTask(VideoDao videoDao) {
            this.videoDao = videoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            videoDao.deleteAll();
            return null;
        }
    }
}
