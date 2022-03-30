package com.hosny.moive_night.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hosny.moive_night.Models.MoiveModel;

//class is for single moive request
public class MoiveResponse {
    @SerializedName("results")//change text to json text in moivemodel so you can change naem on it
    @Expose()//use to allow serazble or deserzable
    private MoiveModel moive;

    public MoiveModel getMoive(){
        return moive;
    }

    @Override
    public String toString() {
        return "MoiveResponse{" +
                "moive=" + moive +
                '}';
    }
}
