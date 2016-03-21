package com.pollux.retrofitdemo.io;

import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by SreeKumar on 13/10/15
 */
public class RestClient {

    private static final String BASE_URL = "https://api.flickr.com/services/rest/";
    private RestServices mRestClient;


    private RestClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /** Handles Log */
        retrofit.client().interceptors().add(logging);

        mRestClient = retrofit.create(RestServices.class);


    }

    private RestServices getRestClient() {
        return mRestClient;
    }


    public static RestServices build() {
        return new RestClient().getRestClient();

    }

}
