package com.hosny.moive_night.Viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hosny.moive_night.Models.MoiveModel;
import com.hosny.moive_night.repositories.MoiveRepository;

import java.util.List;

public class MoiveListViewModel extends ViewModel {

    //class is used for viewmodel

    private MoiveRepository moiveRepository;


    //constructor
    public MoiveListViewModel(){
        moiveRepository=MoiveRepository.getInstance();
    }

    public LiveData<List<MoiveModel>> getmoives(){

        return moiveRepository.getmoives();
    }
    public LiveData<List<MoiveModel>> getpop(){

        return moiveRepository.getpop();
    }

    // 2-calling method in view-model
    public void searchMoiveApi(String query,int pagenumber){
        moiveRepository.searchMoiveApi(query,pagenumber);
    }
    public void searchMoivepop(int pagenumber){
        moiveRepository.searchMoivepop(pagenumber);
    }
    public void searchnextpage(){
        //make when scroll
        moiveRepository.searchnextpage();
    }
}
