package com.softrunapps.rashapplication.view.main.ui.video_content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.softrunapps.rashapplication.R;
import com.softrunapps.rashapplication.component.AppMessage;
import com.softrunapps.rashapplication.model.VideoContentModel;
import com.softrunapps.rashapplication.component.App;

import javax.inject.Inject;

import static com.softrunapps.rashapplication.utils.Utils.getHtmlStyledText;

public class VideoContentFragment extends Fragment implements VideoContentPresenter.View {
    public static final String CONTENT_ID = "content_id";
    private Toolbar toolbar;
    private View viewLoading;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageViewLandscape;
    private WebView webView;

    @Inject
    VideoContentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjection();
    }

    private void initInjection() {
        ((App) requireActivity().getApplication())
                .getAppComponent().inject(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_content, container, false);
        initView(root);
        initBackButton();
        initPresenter();
        return root;
    }

    private void initBackButton() {
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow);
        toolbar.setNavigationOnClickListener(view1 -> NavHostFragment.findNavController(this).popBackStack());
    }


    private void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        viewLoading = view.findViewById(R.id.layout_loading);
        webView = view.findViewById(R.id.web_view);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_layout);
        imageViewLandscape = view.findViewById(R.id.image_view_landscape);

    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.getVideoContent(getArguments().getInt(CONTENT_ID));
    }

    @Override
    public void onGetVideoContent(VideoContentModel.Result videoContent) {
        Glide.with(requireContext().getApplicationContext())
                .load(videoContent.getLandscapeImage()).into(imageViewLandscape);
        collapsingToolbarLayout.setTitle(videoContent.getTitle());

        webView.loadDataWithBaseURL("file:///android_asset/",
                getHtmlStyledText(videoContent.getSummary()),
                "text/html", "utf-8", null);
    }

    @Override
    public void showMessage(String message) {
        AppMessage.with(requireActivity()).showMessage(message);
    }

    @Override
    public void showLoading() {
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        viewLoading.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }
}