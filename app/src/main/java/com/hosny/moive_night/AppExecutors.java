package com.hosny.moive_night;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    //singelton pattern

    private static AppExecutors Instance;
     public static AppExecutors getInstance(){
         if(Instance==null){
             Instance=new AppExecutors();
         }
         return Instance;
     }

     private final ScheduledExecutorService mnetworkio= Executors.newScheduledThreadPool(3);

     public ScheduledExecutorService getMnetworkio(){
         return mnetworkio;
     }
}
