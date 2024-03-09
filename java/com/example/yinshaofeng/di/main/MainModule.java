package com.example.yinshaofeng.di.main;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;

import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.database.saved.clicked.ClickedDao;
import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.database.NewsDao;
import com.example.yinshaofeng.network.MainApi;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    @MainScope
    @Named("defaultRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @MainScope
    static PagedList.Config config(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
    }

    @MainScope
    @Provides
    static MainRepository mainRepositoryInject(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao, ClickedDao clickedDao) {
        return new MainRepository(mainApi, disposable, newsDao, clickedDao);
    }

}
