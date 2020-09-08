package com.softrunapps.rashapplication.view.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.softrunapps.rashapplication.R;
import com.softrunapps.rashapplication.component.AppMessage;
import com.softrunapps.rashapplication.model.VideoListModel;
import com.softrunapps.rashapplication.storage.VideoLocalRepository;
import com.softrunapps.rashapplication.component.App;
import com.softrunapps.rashapplication.utils.Utils;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    private View viewLoading;

    private VideoLocalRepository videoLocalRepository;
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjection();
        setContentView(R.layout.activity_main);
        initView();
        initVideoLocalRepository();
        initPresenter();
        getVideoList();
    }

    private void initInjection() {
        ((App) getApplication()).getAppComponent().inject(this);
    }

    private void initView() {
        viewLoading = findViewById(R.id.layout_loading);
    }

    private void initVideoLocalRepository() {
        videoLocalRepository = new VideoLocalRepository(this.getApplication());
        videoLocalRepository.deleteAll();
    }

    private void initPresenter() {
        presenter.setView(this);
    }

    public void getVideoList() {
        presenter.getVideoList();
    }

    @Override
    public void onGetVideoList(List<VideoListModel.GetContentList> videoList) {
        videoLocalRepository.insert(Utils.convertDataVideoToVideoList(videoList));
    }

    @Override
    public void showMessage(String message) {
        AppMessage.with(this).showMessage(message);
    }

    @Override
    public void showLoading() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        viewLoading.setVisibility(View.GONE);
    }
}