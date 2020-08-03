package hanalyst.application.hanalystclub.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String apiUrl = "https://hanalyst.herokuapp.com/api/";
    private Retrofit retrofit;
    private static RetrofitBuilder retrofitBuilder;

    public RetrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitBuilder getRetrofitBuilder() {
        if (retrofitBuilder == null) {
            retrofitBuilder = new RetrofitBuilder();
        }
        return retrofitBuilder;
    }

    public API getApi(){
        return retrofit.create(API.class);
    }

}
