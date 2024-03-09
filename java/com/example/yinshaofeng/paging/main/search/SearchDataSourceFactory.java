package com.example.yinshaofeng.paging.main.search;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.ui.main.MainRepository;


import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import io.reactivex.disposables.CompositeDisposable;

public class SearchDataSourceFactory extends DataSource.Factory{
    private MainApi mainApi;
    private CompositeDisposable disposable;

    private MainRepository mainRepository;
    private String search ;
    private String category ;

    private String startDate ;
    private String endDate = "2023-09-16";
    private MutableLiveData<SearchDataSource> mutableLiveData;
    public SearchDataSourceFactory(MainApi mainApi, CompositeDisposable disposable, MainRepository repository) {
        this.disposable = disposable;
        this.mainApi = mainApi;
        this.search = "";
        this.category = "";
        this.startDate = "";
        this.mainRepository = repository;
        this.endDate = LocalDate.now().plusDays(1).toString();
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer , ArticlesItem> create() {
        SearchDataSource dataSource = new SearchDataSource(disposable, mainApi, search, category, startDate, endDate,mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }
    public MutableLiveData<SearchDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setSearch(String search){
        this.search = search;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

}
