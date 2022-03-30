package com.hosny.moive_night.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.R;
import com.hosny.moive_night.Utils.Credentials;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoiveRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MoiveModel> mmovies;
    private MoiveLisner moiveLisner;
    private static final int display_pop = 1;
    private static final int display_search = 2;

    public MoiveRecycleView(MoiveLisner moiveLisner) {
        this.moiveLisner = moiveLisner;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_list_item,parent,false);
//        return new MoiveViewHolder(view,moiveLisner) ;
        View v = null;
        if (viewType == display_search) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moive_list_item, parent, false);
            return new MoiveViewHolder(v, moiveLisner);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout, parent, false);
            return new MoiveViewHolder2(v, moiveLisner);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        int decsion = getItemViewType(position);
        if (decsion == display_search) {
            ((MoiveViewHolder) holder).tv_title.setText(mmovies.get(position).getTitle());
            ((MoiveViewHolder) holder).tv_release_data.setText(mmovies.get(position).getRelease_date());
            ((MoiveViewHolder) holder).tv_duration.setText(mmovies.get(position).getoriginal_language());
            ((MoiveViewHolder) holder).ratingBar.setRating(mmovies.get(position).getVote_average() / 2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + mmovies.get(position).getPoster_path()).placeholder(R.drawable.ic_baseline_get_app_24).into(((MoiveViewHolder) holder).movie_image);
        } else {
            ((MoiveViewHolder2) holder).tv_title_pop.setText(mmovies.get(position).getTitle());
            ((MoiveViewHolder2) holder).tv_release_data_pop.setText(mmovies.get(position).getRelease_date());
            ((MoiveViewHolder2) holder).tv_duration_pop.setText(mmovies.get(position).getoriginal_language());
            ((MoiveViewHolder2) holder).ratingBar_pop.setRating(mmovies.get(position).getVote_average() / 2);
            Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + mmovies.get(position).getPoster_path()).placeholder(R.drawable.ic_baseline_get_app_24).into(((MoiveViewHolder2) holder).movie_image_pop);


        }


    }
    public void setMmovies(List<MoiveModel> mmovies){
        this.mmovies = mmovies;
        notifyDataSetChanged();
    }
public void cleardata() {
    mmovies.clear();
}

    public MoiveModel getname ( int postion){
        return mmovies.get(postion);
    }


    @Override
    public int getItemCount() {
        if (mmovies != null) {
            return mmovies.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(Credentials.popular){
            return display_pop;
        }else {
            return display_search;
        }
    }
}
