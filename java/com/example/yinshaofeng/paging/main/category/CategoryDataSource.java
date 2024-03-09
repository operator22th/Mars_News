package com.example.yinshaofeng.paging.main.category;

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

public class CategoryDataSource extends PageKeyedDataSource<Integer, ArticlesItem> {
    private static final String TAG = "CategoryDataSource";
    private CompositeDisposable disposable;
    private MainApi mainApi;
    private String category;
    private MainRepository mainRepository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    CategoryDataSource(CompositeDisposable disposable, MainApi mainApi, String category, MainRepository mainRepository) {
        this.disposable = disposable;
        this.mainRepository = mainRepository;
        this.mainApi = mainApi;
        mutableLiveData = new MutableLiveData<>();
        if(category.equalsIgnoreCase("全部"))
            category = "";
        this.category = category;
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ArticlesItem> loadCallback) {
        LocalDate localDate = LocalDate.now().plusDays(1);
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(
                mainApi.getTopHead(loadParams.key,loadParams.requestedLoadSize,"",localDate.toString(),"",category)
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
        LocalDate localDate = LocalDate.now().plusDays(1);
        disposable.add(
                mainApi.getTopHead(1,loadInitialParams.requestedLoadSize,"",localDate.toString(),"",category)
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
