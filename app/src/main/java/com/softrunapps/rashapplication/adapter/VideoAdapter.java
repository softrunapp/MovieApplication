package com.softrunapps.rashapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softrunapps.rashapplication.R;
import com.softrunapps.rashapplication.component.AppRipple;
import com.softrunapps.rashapplication.storage.room.Video;

public class VideoAdapter extends ListAdapter<Video, VideoAdapter.ViewHolder> {
    private OnItemClickListener listener;

    public VideoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Video> DIFF_CALLBACK = new DiffUtil.ItemCallback<Video>() {
        @Override
        public boolean areItemsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
            return oldItem.getContentID().equals(newItem.getContentID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
            return oldItem.getContentID().equals(newItem.getContentID());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.render(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewSummary, textViewType;
        private ImageView imageViewThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewType = itemView.findViewById(R.id.text_view_type);
            textViewSummary = itemView.findViewById(R.id.text_view_summary);
            imageViewThumb = itemView.findViewById(R.id.image_view_thumb);

            AppRipple.setViewRipple(itemView);
        }

        void render(Video video) {
            textViewTitle.setText(video.getTitle());
            textViewType.setText(video.getVideoType());
            textViewSummary.setText(video.getSummary());
            Glide.with(imageViewThumb).load(video.getThumbImage()).into(imageViewThumb);
            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(video);
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Video video);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
