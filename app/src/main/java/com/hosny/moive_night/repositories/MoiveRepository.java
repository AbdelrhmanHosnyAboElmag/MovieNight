package com.hosny.moive_night.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.Request.MovieApiClient;

import java.util.List;

public class MoiveRepository {
    //class act as repositiries

    private MovieApiClient movieApiClient;

    private static MoiveRepository instance;

    private String mquery;
    private int mpagenumber;


    public static MoiveRepository getInstance(){
        if(instance==null){
            instance=new MoiveRepository();
        }
        return instance;


    }
    private MoiveRepository(){
        movieApiClient=MovieApiClient.getInstance();
    }

    public MovieApiClient getMovieApiClient() {
        return movieApiClient;
    }

    public LiveData<List<MoiveModel>> getmoives(){return movieApiClient.getmoives() ;}
    public LiveData<List<MoiveModel>> getpop(){return movieApiClient.getmoivespop() ;}


    //1-calling method in repository
    public void searchMoiveApi(String query,int pagenumber){
        mquery=query;
        mpagenumber=pagenumber;
        movieApiClient.searchMoiveApi(query,pagenumber);
    }

    public void searchMoivepop(int pagenumber){
        mpagenumber=pagenumber;
        movieApiClient.searchMoiveApipop(pagenumber);
    }

    public void searchnextpage(){
        //make when scroll

        searchMoiveApi(mquery,mpagenumber+1);
    }

}
