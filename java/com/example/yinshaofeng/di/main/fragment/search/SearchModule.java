package com.example.yinshaofeng.di.main.fragment.search;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.di.ViewModelKey;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.paging.main.search.SearchDataSourceFactory;
import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.ui.main.fragment.search.SearchAdapter;
import com.example.yinshaofeng.ui.main.fragment.search.SearchViewModel;


import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class SearchModule {


    @SearchScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @SearchScope
    static SearchDataSourceFactory provideSearchDataSourceFactory(CompositeDisposable disposable , MainApi mainApi, MainRepository repository) {
        return new SearchDataSourceFactory(mainApi, disposable, repository);
    }

    @SearchScope
    @Provides
    static SearchAdapter provideSearchAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new SearchAdapter(requestManager, requestOptions);
    }

    @Binds
    @IntoMap
    @SearchScope
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindViewModel(SearchViewModel categoryViewModel);
}
