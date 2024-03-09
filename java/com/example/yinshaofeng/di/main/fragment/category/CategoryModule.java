package com.example.yinshaofeng.di.main.fragment.category;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.di.ViewModelKey;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.paging.main.category.CategoryDataSourceFactory;
import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.ui.main.fragment.category.CategoryItemAdapter;
import com.example.yinshaofeng.ui.main.fragment.category.CategoryMainAdapter;
import com.example.yinshaofeng.ui.main.fragment.category.CategoryViewModel;


import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class CategoryModule {


    @Named("vertical")
    @CategoryScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Named("horizontal")
    @CategoryScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    @CategoryScope
    static CategoryDataSourceFactory provideCategoryDataSourceFactory(CompositeDisposable disposable , MainApi mainApi, MainRepository repository) {
        return new CategoryDataSourceFactory(mainApi, disposable, repository);
    }

    @CategoryScope
    @Provides
    static CategoryMainAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new CategoryMainAdapter(requestManager, requestOptions);
    }



    @Binds
    @IntoMap
    @CategoryScope
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindHomeViewModel(CategoryViewModel categoryViewModel);
}
