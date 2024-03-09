package com.example.yinshaofeng.paging.main.home;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.model.ArticlesItem;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeDataSourceFactory extends DataSource.Factory {

    private MainRepository mainRepository;
    private CompositeDisposable disposable;
    private MutableLiveData<HomeDataSource> mutableLiveData;

    @Inject
    public HomeDataSourceFactory(MainRepository mainRepository, CompositeDisposable disposable) {
        this.mainRepository = mainRepository;
        this.disposable = disposable;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer , ArticlesItem> create() {
        HomeDataSource dataSource = new HomeDataSource(disposable, mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<HomeDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}