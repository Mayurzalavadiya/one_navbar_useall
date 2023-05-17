package com.myapplication.api;


import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final int TIME = 60 * 5;
    private static final String TAG = RestClient.class.getSimpleName();

    private static final String BASE_URL = "https://dummyjson.com/"; //Local Url


   // private static final String BASE_URL = "http://tgate.texas-alarms.com/api/"; //Live Url


    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static RestClient clientInstance;

    private static OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .connectTimeout(TIME, TimeUnit.SECONDS)
            .readTimeout(TIME, TimeUnit.SECONDS)
            .writeTimeout(TIME, TimeUnit.SECONDS)
            .addInterceptor(logging)
            /*.addInterceptor(new UnauthorisedInterceptor())*/
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                        requestBuilder.addHeader("authtoken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoidGdhdGVfa2V5IiwibmFtZSI6IlRnYXRlIEtleSIsIkFQSV9USU1FIjoxNjMxMTAyMTA2fQ.CXx5G1edmSiz2Hf17dS4jzH7kOp7kD7xRqSYcK6ewko");

                        Request request = requestBuilder.build();
                    /*Log.e("Response Body", response.body().toString());*/
                    //Log.w("CT_", "--->   " + " Passing Params : " + bodyToString(request));
                    return chain.proceed(request);
                }
            })
            .build();
    private ApiInterface apiInterface;

    private RestClient() {
        /*Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();*/
        /*builder.addConverterFactory(GsonConverterFactory.create(gson));*/
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(httpClient);
        Retrofit retrofit = builder.build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RestClient getInstance() {
        if (clientInstance == null) {
            clientInstance = new RestClient();
        }
        return clientInstance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }


    private static String bodyToString(final Request request) {
        try {
            final Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}