package com.example.yinshaofeng.di;

import androidx.lifecycle.ViewModel;

import com.example.yinshaofeng.ui.details.DetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel.class)
    abstract ViewModel bindDetailsViewModel(DetailsViewModel detailsViewModel);
}
