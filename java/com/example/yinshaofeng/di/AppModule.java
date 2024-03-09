package com.example.yinshaofeng.di;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.database.NewsDao;
import com.example.yinshaofeng.database.NewsDatabase;
import com.example.yinshaofeng.database.saved.clicked.ClickedDao;
import com.example.yinshaofeng.database.saved.SavedDao;
import com.example.yinshaofeng.model.converter.Deserializer;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(String.class, new Deserializer())
                .create();

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideOkHttpInterceptor (){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttp(HttpLoggingInterceptor logging){
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Singleton
    @Provides
    static CompositeDisposable provideDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    @Singleton
    static RequestManager provideGlide(Application application){
        return Glide.with(application);
    }

    @Singleton
    @Provides
    static NewsDatabase provideRoomDatabase(Application application){
        return Room.databaseBuilder(application,
                        NewsDatabase.class, "news_database")
                .build();
    }


    @Singleton
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }


    @Singleton
    @Provides
    static SavedDao provideSavedDao(NewsDatabase database) {
        return database.savedDao();
    }

    @Singleton
    @Provides
    static NewsDao provideNewsDao(NewsDatabase database) {
        return database.newsDao();
    }

    @Singleton
    @Provides
    static ClickedDao provideClickedDao(NewsDatabase database) {
        return database.clickedDao();
    }

    @Provides
    @Singleton
    @Named("circleRequestOption")
    static RequestOptions provideCircleRequestOptions() {
        return RequestOptions.circleCropTransform();
    }

    @Provides
    @Singleton
    static ConnectivityManager connectivityManager(Application application){
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


}
