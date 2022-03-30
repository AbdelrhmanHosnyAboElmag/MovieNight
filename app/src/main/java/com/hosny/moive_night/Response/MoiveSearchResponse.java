package com.hosny.moive_night.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hosny.moive_night.Models.MoiveModel;

import java.util.List;

//class is for getting mutiple moive -popular moive
public class MoiveSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MoiveModel> moives;

    public int getTotal_count(){
        return total_count;
    }

    public List<MoiveModel> getMoives(){
        return  moives;
    }

    @Override
    public String toString() {
        return "MoiveSearchResponse{" +
                "total_count=" + total_count +
                ", moives=" + moives +
                '}';
    }
}
