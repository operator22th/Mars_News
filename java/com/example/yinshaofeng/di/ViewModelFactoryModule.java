package com.example.yinshaofeng.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.yinshaofeng.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

}
