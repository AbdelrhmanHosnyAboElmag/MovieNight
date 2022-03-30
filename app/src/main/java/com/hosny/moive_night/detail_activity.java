package com.hosny.moive_night;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hosny.moive_night.Models.MoiveModel;

public class detail_activity extends AppCompatActivity {
    TextView tv_title,tv_plot;
    ImageView poster;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tv_title=findViewById(R.id.textView);
        tv_plot=findViewById(R.id.textView2);
        poster=findViewById(R.id.imageView);
        ratingBar=findViewById(R.id.ratingBar2);
        getdatafromintent();
    }

    private void getdatafromintent() {
        if(getIntent().hasExtra("movie")){
            MoiveModel moiveModel=getIntent().getParcelableExtra("movie");
            tv_title.setText(moiveModel.getTitle());
            tv_plot.setText(moiveModel.getOverview());
            ratingBar.setRating((moiveModel.getVote_average())/2);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+moiveModel.getPoster_path()).into(poster);
        }
    }
}