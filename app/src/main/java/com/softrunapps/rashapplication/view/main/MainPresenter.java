package com.softrunapps.rashapplication.view.main;

import com.softrunapps.rashapplication.base.Presenter;
import com.softrunapps.rashapplication.data.DataListener;
import com.softrunapps.rashapplication.data.VideoFactory;
import com.softrunapps.rashapplication.data.VideoRepository;
import com.softrunapps.rashapplication.model.VideoListModel;

import java.util.List;

import javax.inject.Inject;

import static com.softrunapps.rashapplication.utils.Utils.getRequestJsonObject;

public class MainPresenter extends Presenter<MainPresenter.View> {
    private final VideoRepository videoRepository;
    private int page = 1;

    @Inject
    public MainPresenter(VideoFactory videoFactory) {
        videoRepository = videoFactory.createVideoFactory();
    }

    public void getVideoList() {
        if (isFirstPage())
            getView().showLoading();
        videoRepository.getVideoList(getRequestJsonObject(page++),
                new DataListener<List<VideoListModel.GetContentList>>() {
                    @Override
                    public void onResponse(List<VideoListModel.GetContentList> response) {
                        getView().onGetVideoList(response);
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(String message) {
                        getView().hideLoading();
                        getView().showMessage(message);
                    }
                });
    }

    private boolean isFirstPage() {
        return page == 1;
    }

    public interface View extends Presenter.View {
        void onGetVideoList(List<VideoListModel.GetContentList> videoList);
    }
}
