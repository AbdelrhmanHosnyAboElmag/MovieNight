package com.hosny.moive_night;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.Request.Services;
import com.hosny.moive_night.Response.MoiveSearchResponse;
import com.hosny.moive_night.Utils.Credentials;
import com.hosny.moive_night.Utils.MoiveApi;
import com.hosny.moive_night.Viewmodels.MoiveListViewModel;
import com.hosny.moive_night.adapters.MoiveLisner;
import com.hosny.moive_night.adapters.MoiveRecycleView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoiveListActivity extends AppCompatActivity implements MoiveLisner {
    private RecyclerView recyclerView;
    private MoiveRecycleView moivercycleadapter;
    //viewmodel

    private MoiveListViewModel moiveListViewModel;

    boolean ispopular=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv);
        //tool bar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        moiveListViewModel= new ViewModelProvider(this).get(MoiveListViewModel.class);

        //search view
        setupsearchview();
        //set rv
        configrecycleview();

        //calling the observers
        observeranychange();

        observerpopularmoive();

        moiveListViewModel.searchMoivepop(1);


//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchMovieApi("fast",1);
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moivercycleadapter.cleardata();
        observeranychange();
        observerpopularmoive();
    }

    private void observerpopularmoive() {
        moiveListViewModel.getpop().observe(this, new Observer<List<MoiveModel>>() {
            @Override
            public void onChanged(List<MoiveModel> moiveModels) {
                //observing any data chaning
                if(moiveModels !=null){
                    for (MoiveModel moiveModel:moiveModels){
                        //get data
                        if(moiveModel!=null) {
                            Log.v("tag", "onchange" + moiveModel.getTitle());
                            moivercycleadapter.setMmovies(moiveModels);
                        }

                    }
                }

            }
        });

    }


    private void setupsearchview() {
        final SearchView searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                moiveListViewModel.searchMoiveApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ispopular=false;
            }
        });
    }


    //observing any data change
    private void observeranychange(){

        moiveListViewModel.getmoives().observe(this, new Observer<List<MoiveModel>>() {
            @Override
            public void onChanged(List<MoiveModel> moiveModels) {
                //observing any data chaning
                    if(moiveModels !=null){
                        for (MoiveModel moiveModel:moiveModels){
                            //get data
                            if(moiveModel!=null) {
                                Log.v("tag", "onchange" + moiveModel.getTitle());
                                moivercycleadapter.setMmovies(moiveModels);
                            }

                        }
                    }

            }
        });





    }





    private void configrecycleview(){
        moivercycleadapter =new MoiveRecycleView(this);
        recyclerView.setAdapter(moivercycleadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if(recyclerView.canScrollVertically(1)){
                    moiveListViewModel.searchnextpage();
                }
            }
        });
    }

    @Override
    public void onmoiveclick(int position) {
//        Toast.makeText(this, moivercycleadapter.getname(position)+"", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,detail_activity.class);
        intent.putExtra("movie",moivercycleadapter.getname(position));
        startActivity(intent);
    }

    @Override
    public void oncatagoryclick(String catagory) {

    }


//before use mvvm
//    private void GetRetrofitRespnse() {
//        MoiveApi moiveApi= Services.getMoiveApi();
//        Call<MoiveSearchResponse> responseCall= moiveApi
//                .searchMoive(Credentials.API_KEY,
//                        "jack reacher","1");
//
//        responseCall.enqueue(new Callback<MoiveSearchResponse>() {
//            @Override
//            public void onResponse(Call<MoiveSearchResponse> call, Response<MoiveSearchResponse> response) {
//                        if(response.code()==200){
//                            Log.v("tag","the response"+response.body().toString());
//                            List<MoiveModel> moives=new ArrayList<>(response.body().getMoives());
//                            Log.v("taghosny","test"+response.body().getTotal_count());
//                            for (MoiveModel moive:moives) {
//                                Log.v("tag","the relase date"+moive.getRelease_date());
//
//                            }
//                        }
//                        else {
//                            try {
//                                Log.v("tag","error"+response.errorBody().string());
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//            }
//
//            @Override
//            public void onFailure(Call<MoiveSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
//    private void GetRetrofitRespnse_byid() {
//        MoiveApi moiveApi= Services.getMoiveApi();
//        Call<MoiveModel> responseCall= moiveApi
//                .getmoive(550,Credentials.API_KEY);
//
//        responseCall.enqueue(new Callback<MoiveModel>() {
//            @Override
//            public void onResponse(Call<MoiveModel> call, Response<MoiveModel> response) {
//                if(response.code()==200){
//                    MoiveModel moiveModel=response.body();
//                    Log.v("tag","id:"+moiveModel.getId());
//
//                }
//                else {
//                    try {
//                        Log.v("tag","error"+response.errorBody().string());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MoiveModel> call, Throwable t) {
//
//            }
//        });
//    }
}