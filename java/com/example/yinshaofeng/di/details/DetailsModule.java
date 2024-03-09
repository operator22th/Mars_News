package com.example.yinshaofeng.di.details;

import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.database.saved.SavedDao;
import com.example.yinshaofeng.ui.details.DetailsRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
class DetailsModule {

    @Provides
    @DetailsScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @DetailsScope
    static DetailsRepository provideDetailsRepository(SavedDao savedDao, CompositeDisposable disposable){
        return new DetailsRepository(savedDao, disposable);
    }
}