package com.example.yinshaofeng.paging.main.home;

import android.util.Log;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.ui.main.MainRepository;
import com.example.yinshaofeng.utils.DataStatus;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;


import org.threeten.bp.LocalDate;

import io.reactivex.disposables.CompositeDisposable;
//泛型参数 Integer 表示分页键的类型
public class HomeDataSource extends PageKeyedDataSource<Integer, ArticlesItem>{
    private static final String TAG = "NewsDataSource";
    private CompositeDisposable disposable;
    private MainRepository mainRepository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public HomeDataSource(CompositeDisposable disposable, MainRepository mainRepository) {
        this.disposable = disposable;
        this.mainRepository = mainRepository;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
        // 在这里实现下一页数据的加载逻辑
        LocalDate localDate = LocalDate.now().plusDays(1);
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(
                mainRepository.fetchFromApi(params.key, params.requestedLoadSize,"",localDate.toString(),"","")
                .subscribe(data -> {
                            callback.onResult(data.getArticles(), params.key + 1);
                            mutableLiveData.postValue(DataStatus.LOADED);
                            mainRepository.saveData(data);
                        }, throwable -> disposable.add(
                                mainRepository.fetchFromDB(10, params.key)
                                        .subscribe(data -> {
                                                    Log.d(TAG, "loadMMM: New Room " + data.size());
                                                    callback.onResult(data, params.key + 10);
                                                    mutableLiveData.postValue(DataStatus.LOADED);
                                                },
                                                throwable1 -> Log.d(TAG, "database  ERROR " + throwable1))
                        )

                ));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> loadParams, @NonNull LoadCallback<Integer, ArticlesItem> loadCallback) {
        // 在这里实现上一页数据的加载逻辑（如果需要）
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> loadInitialParams, @NonNull LoadInitialCallback<Integer, ArticlesItem> loadInitialCallback) {
        // 在这里实现获取初始数据的逻辑
        mutableLiveData.postValue(DataStatus.LOADING);
        LocalDate localDate = LocalDate.now().plusDays(1);
        disposable.add(mainRepository.fetchFromApi(1,loadInitialParams.requestedLoadSize,"",localDate.toString(),"","")
                .subscribe(data -> {
                            if (data.getArticles().isEmpty())
                                throw new NullPointerException();

                            loadInitialCallback.onResult(data.getArticles(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
                            mainRepository.saveData(data);
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
