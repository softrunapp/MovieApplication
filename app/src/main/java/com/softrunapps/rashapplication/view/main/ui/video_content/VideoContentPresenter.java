package com.softrunapps.rashapplication.view.main.ui.video_content;

import com.google.gson.JsonObject;
import com.softrunapps.rashapplication.base.Presenter;
import com.softrunapps.rashapplication.data.DataListener;
import com.softrunapps.rashapplication.data.VideoFactory;
import com.softrunapps.rashapplication.data.VideoRepository;
import com.softrunapps.rashapplication.model.VideoContentModel;

import javax.inject.Inject;

public class VideoContentPresenter extends Presenter<VideoContentPresenter.View> {

    private final VideoRepository videoRepository;
    private boolean viewDestroyed;

    @Inject
    public VideoContentPresenter(VideoFactory videoFactory) {
        videoRepository = videoFactory.createVideoFactory();
    }

    public void getVideoContent(int contentId) {
        getView().showLoading();
        videoRepository.getVideoContent("fa-IR", getRequestJsonObject(contentId),
                new DataListener<VideoContentModel.Result>() {
                    @Override
                    public void onResponse(VideoContentModel.Result response) {
                        if (viewDestroyed) {
                            return;
                        }
                        getView().onGetVideoContent(response);
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(String message) {
                        getView().showMessage(message);
                    }
                });
    }

    void onDestroyView() {
        viewDestroyed = true;
    }

    private JsonObject getRequestJsonObject(int contentId) {
        JsonObject propertiesJsonObject = new JsonObject();
        propertiesJsonObject.addProperty("RequestId", contentId);
        JsonObject requestJsonObject = new JsonObject();
        requestJsonObject.add("request", propertiesJsonObject);
        return requestJsonObject;
    }

    public interface View extends Presenter.View {
        void onGetVideoContent(VideoContentModel.Result videoContent);
    }
}
