package com.example.yinshaofeng.paging.main.category;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.ui.main.MainRepository;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryDataSourceFactory extends DataSource.Factory{
    private MainApi mainApi;
    private CompositeDisposable disposable;
    private MainRepository mainRepository;
    private String category;
    private MutableLiveData<CategoryDataSource> mutableLiveData;


    public CategoryDataSourceFactory(MainApi mainApi, CompositeDisposable disposable, MainRepository repository) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.category = "";
        this.mainRepository = repository;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer , ArticlesItem> create() {
        Log.d("MMMMMM", "create: " );
        CategoryDataSource dataSource = new CategoryDataSource(disposable, mainApi,category,mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<CategoryDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
