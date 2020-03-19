package com.mrerror.parachut.NetWork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroWeb {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {


        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();


//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd")
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .create();


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Urls.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();

        }
        return retrofit;

    }


    public static Retrofit getLocationClint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://maps.google.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }

}
