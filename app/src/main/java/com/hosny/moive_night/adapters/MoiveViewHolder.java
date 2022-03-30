package com.hosny.moive_night.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hosny.moive_night.R;

import org.jetbrains.annotations.NotNull;

public class MoiveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_duration, tv_release_data, tv_title;
    ImageView movie_image;
    RatingBar ratingBar;
    MoiveLisner moiveLisner;

    public MoiveViewHolder(@NonNull @NotNull View itemView, MoiveLisner moiveLisner) {
        super(itemView);
        this.moiveLisner = moiveLisner;
        tv_release_data = itemView.findViewById(R.id.tv_release_data);
        tv_duration = itemView.findViewById(R.id.tv_duration);
        tv_title = itemView.findViewById(R.id.tv_title);
        movie_image = itemView.findViewById(R.id.moive_image);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        moiveLisner.onmoiveclick(getAdapterPosition());
    }
}
