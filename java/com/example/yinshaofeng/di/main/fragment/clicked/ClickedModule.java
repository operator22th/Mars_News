package com.example.yinshaofeng.di.main.fragment.clicked;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.yinshaofeng.di.ViewModelKey;
import com.example.yinshaofeng.di.main.fragment.saved.SavedScope;
import com.example.yinshaofeng.ui.main.fragment.clicked.ClickedAdapter;
import com.example.yinshaofeng.ui.main.fragment.clicked.ClickedViewModel;
import com.example.yinshaofeng.ui.main.fragment.saved.SavedAdapter;
import com.example.yinshaofeng.ui.main.fragment.saved.SavedViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class ClickedModule {
    @ClickedScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @ClickedScope
    @Provides
    static ClickedAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new ClickedAdapter(requestManager,requestOptions);
    }

    @ClickedScope
    @Named("clicked")
    @Provides
    static CompositeDisposable provideDisposable() {
        return new CompositeDisposable();
    }

    @Binds
    @IntoMap
    @ViewModelKey(ClickedViewModel.class)
    abstract ViewModel bindClickedViewModel(ClickedViewModel homeViewModel);
}
