package com.hosny.moive_night.Request;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hosny.moive_night.AppExecutors;
import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.MoiveListActivity;
import com.hosny.moive_night.Response.MoiveSearchResponse;
import com.hosny.moive_night.Utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //live data
    private MutableLiveData<List<MoiveModel>> mMoives;
    private static MovieApiClient instance;
    private RetrieveMoivesRunnable runnable;
    //live data for popular moive
    private MutableLiveData<List<MoiveModel>>mmoivepopular;
    //make popular moive
    private RetrieveMoivesRunnablepop popular_runnable;
    public static MovieApiClient getInstance(){
            if(instance==null){
                instance=new MovieApiClient();
            }
        return instance;
    }
    private MovieApiClient(){
        mMoives=new MutableLiveData<>();
        mmoivepopular=new MutableLiveData<>();
    }

    public LiveData<List<MoiveModel>> getmoives(){
        return mMoives;
    }
    public LiveData<List<MoiveModel>> getmoivespop(){
        return mmoivepopular;
    }


    public void searchMoiveApi(String query,int pagenumber){
        if(runnable!=null){
            runnable=null;
        }
        runnable=new RetrieveMoivesRunnable(pagenumber,query);
        final Future myhandler= AppExecutors.getInstance().getMnetworkio().submit(runnable);

        AppExecutors.getInstance().getMnetworkio().schedule(new Runnable() {
            @Override
            public void run() {
                myhandler.cancel(true);
                Log.v("failR","fail");
            }
        },3000, TimeUnit.MILLISECONDS);

    }
    public void searchMoiveApipop(int pagenumber){
        if(popular_runnable!=null){
            popular_runnable=null;
        }
        popular_runnable=new RetrieveMoivesRunnablepop(pagenumber);
        final Future myhandler2= AppExecutors.getInstance().getMnetworkio().submit(popular_runnable);

        AppExecutors.getInstance().getMnetworkio().schedule(new Runnable() {
            @Override
            public void run() {
                myhandler2.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);

    }
    //retriving data from restapi by runnable class
    private class  RetrieveMoivesRunnable implements Runnable{
        private int pagenumber;
        private String query;
        boolean cancelrequest;

        public RetrieveMoivesRunnable(int pagenumber, String query) {
            this.pagenumber = pagenumber;
            this.query = query;
        }

        @Override
        public void run() {
            //getting the response object
            try {
                Response response=getmovies(query,pagenumber).execute();
                if(cancelrequest){
                    return;
                }
                if(response.code()==200){
                    List<MoiveModel>list=new ArrayList<>(((MoiveSearchResponse)response.body()).getMoives());
                    if(pagenumber==1){
                        //send data to livedata
                        //postvalue:use for background thread
                        //setvalue :not use for background thread
                        mMoives.postValue(list);
                    }else {
                        List<MoiveModel>currentmoives=mMoives.getValue();//return true if it work
                        currentmoives.addAll(list);//handle it ex:one item and one no will send item that present
                        mMoives.postValue(currentmoives);
                    }
                }else {
                    String error=response.errorBody().string();
                    Log.v("tag","error"+error);
                    mMoives.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMoives.postValue(null);
            }

        }
        private Call<MoiveSearchResponse>getmovies(String query,int pagenumber){
            return Services.getMoiveApi().searchMoive(Credentials.API_KEY,query,String.valueOf(pagenumber));
        }
        private void setCancelrequest(){
            Log.v("tag","cancelling search request");
            cancelrequest=true;
        }
    }

    private class  RetrieveMoivesRunnablepop implements Runnable{
        private int pagenumber;
        boolean cancelrequest;

        public RetrieveMoivesRunnablepop(int pagenumber) {
            this.pagenumber = pagenumber;
        }

        @Override
        public void run() {
            //getting the response object
            try {
                Response response2=getpop(pagenumber).execute();
                if(cancelrequest){
                    return;
                }
                if(response2.code()==200){
                    List<MoiveModel>list=new ArrayList<>(((MoiveSearchResponse)response2.body()).getMoives());
                    if(pagenumber==1){
                        //send data to livedata
                        //postvalue:use for background thread
                        //setvalue :not use for background thread
                        mmoivepopular.postValue(list);
                    }else {
                        List<MoiveModel>currentmoives=mmoivepopular.getValue();//return true if it work
                        currentmoives.addAll(list);//handle it ex:one item and one no will send item that present
                        mmoivepopular.postValue(currentmoives);
                    }
                }else {
                    String error=response2.errorBody().string();
                    Log.v("tag","error"+error);
                    mmoivepopular.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mmoivepopular.postValue(null);
            }

        }
        private Call<MoiveSearchResponse>getmovies(String query,int pagenumber){
            return Services.getMoiveApi().searchMoive(Credentials.API_KEY,query,String.valueOf(pagenumber));
        }
        private Call<MoiveSearchResponse>getpop(int pagenumber){
            return Services.getMoiveApi().getpopular(Credentials.API_KEY,String.valueOf(pagenumber));
        }
        private void setCancelrequest(){
            Log.v("tag","cancelling search request");
            cancelrequest=true;
        }
    }


}
