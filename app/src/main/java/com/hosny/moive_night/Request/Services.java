package com.hosny.moive_night.Request;

import com.hosny.moive_night.Utils.Credentials;
import com.hosny.moive_night.Utils.MoiveApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {
    // singelton pattern do build retrofit
    private static Retrofit.Builder retrofit_builder=new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit=retrofit_builder.build();
    private static com.hosny.moive_night.Utils.MoiveApi moiveApi=retrofit.create(MoiveApi.class);
    public static MoiveApi getMoiveApi(){
        return moiveApi;
    }

}
