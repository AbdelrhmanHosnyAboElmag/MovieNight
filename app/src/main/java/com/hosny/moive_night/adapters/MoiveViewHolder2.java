package com.hosny.moive_night.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hosny.moive_night.R;

import org.jetbrains.annotations.NotNull;

public class MoiveViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_duration_pop,tv_release_data_pop,tv_title_pop;
    ImageView movie_image_pop;
    RatingBar ratingBar_pop;
    MoiveLisner moiveLisner;
    public MoiveViewHolder2(@NonNull @NotNull View itemView,MoiveLisner moiveLisner) {
        super(itemView);
        this.moiveLisner=moiveLisner;
        tv_release_data_pop= itemView.findViewById(R.id.tv_duration_pop);
        tv_duration_pop= itemView.findViewById(R.id.tv_duration_pop);
        tv_title_pop= itemView.findViewById(R.id.tv_title_pop);
        movie_image_pop= itemView.findViewById(R.id.moive_image_pop);
        ratingBar_pop= itemView.findViewById(R.id.ratingBar_pop);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        moiveLisner.onmoiveclick(getAdapterPosition());
    }
}
