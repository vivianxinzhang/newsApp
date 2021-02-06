package com.laioffer.tinnews.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This class is responsible for providing a configured Retrofit instance,
// that can then instantiate a NewsApi implementation.
public class RetrofitClient {
    private static final String API_KEY = "0c530ab31bfe4a7aa8279ba75bfcf60c";
    private static final String BASE_URL = "https://newsapi.org/v2/";   // 1. base URL

    public static Retrofit newInstance(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())    // 2. A header interceptor
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  // 3. Gson adapter. This is to tell how a JSON response can be deserialized into model classes.
                .client(okHttpClient)
                .build();
    }

    // A header interceptor. You can attach custom or standard header information to all requests.
    public static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
}
