package com.softrunapps.rashapplication.view.main.ui.video_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softrunapps.rashapplication.R;
import com.softrunapps.rashapplication.adapter.VideoAdapter;
import com.softrunapps.rashapplication.storage.room.Video;
import com.softrunapps.rashapplication.view.main.MainActivity;
import com.softrunapps.rashapplication.view.main.ui.video_content.VideoContentFragment;

import java.util.List;


public class VideoListFragment extends Fragment {
    private boolean canGetNewPage = false;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_list, container, false);
        initView(root);
        initAdapter();
        initViewModel();
        return root;
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
    }

    private void initAdapter() {
        videoAdapter = new VideoAdapter();
        videoAdapter.setOnItemClickListener(video -> {
            Bundle bundle = new Bundle();
            bundle.putInt(VideoContentFragment.CONTENT_ID, video.getContentID());
            NavHostFragment.findNavController(VideoListFragment.this).navigate(R.id.action_navigation_video_list_to_navigation_video_content, bundle);
        });

        recyclerView.setAdapter(videoAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {
                    if (canGetNewPage) {
                        canGetNewPage = false;
                        ((MainActivity) requireActivity()).getVideoList();
                    }
                }
            }
        });

    }

    private void initViewModel() {
        VideoListViewModel videoListViewModel = ViewModelProviders.of(this).get(VideoListViewModel.class);
        videoListViewModel.getAll().observe(requireActivity(), new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                canGetNewPage = true;
                videoAdapter.submitList(videos);
            }
        });

    }

}