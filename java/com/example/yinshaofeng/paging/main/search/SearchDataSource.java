package com.example.yinshaofeng.paging.main.search;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.network.MainApi;
import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.utils.DataStatus;

import org.threeten.bp.LocalDate;

import io.reactivex.disposables.CompositeDisposable;

public class SearchDataSource extends PageKeyedDataSource<Integer, ArticlesItem> {
    private static final String TAG = "SearchDataSource";
    private CompositeDisposable disposable;
    private MainApi mainApi;
    private MainRepository mainRepository;
    private String startDate = "" ;

    private String endDate = LocalDate.now().plusDays(1).toString();

    private String search = "" ;

    private String category = "";
    private MutableLiveData<DataStatus> mutableLiveData;
    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }
    public SearchDataSource(CompositeDisposable disposable, MainApi mainApi, String search, String category, String startDate, String endDate, MainRepository mainRepository) {
        this.disposable = disposable;
        this.mainApi = mainApi;
        this.search = search;
        this.category = category;
        this.startDate = startDate;
        this.mainRepository = mainRepository;
        this.endDate = endDate;
        mutableLiveData = new MutableLiveData<>();
    }
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ArticlesItem> loadCallback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(
                mainApi.getTopHead(loadParams.key,loadParams.requestedLoadSize,startDate,endDate,search,category)
                        .subscribe(data -> {
                                    loadCallback.onResult(data.getArticles(), loadParams.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                }, throwable -> disposable.add(
                                mainRepository.fetchFromDB(10, loadParams.key)
                                        .subscribe(data -> {
                                                    Log.d(TAG, "loadMMM: New Room " + data.size());
                                                    loadCallback.onResult(data, loadParams.key + 10);
                                                    mutableLiveData.postValue(DataStatus.LOADED);
                                                },
                                                throwable1 -> Log.d(TAG, "database  ERROR " + throwable1)))
                        ));

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ArticlesItem> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, ArticlesItem> loadInitialCallback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(
                mainApi.getTopHead(1,loadInitialParams.requestedLoadSize,startDate,endDate,search,category)
                        .subscribe(data -> {
                                    if (data.getArticles().isEmpty())
                                        throw new NullPointerException();
                                    loadInitialCallback.onResult(data.getArticles(), null, 2);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                }, throwable ->
                                disposable.add(mainRepository.fetchFromDB(10, 0)
                                        .subscribe(data -> {
                                            if (data.isEmpty())
                                                mutableLiveData.postValue(DataStatus.ERROR);
                                            else {
                                                mutableLiveData.postValue(DataStatus.LOADED);
                                                loadInitialCallback.onResult(data, null, 10);
                                            }
                                        }, error -> Log.d(TAG, "loadInitial: " + error)))
                        ));
    }
}
