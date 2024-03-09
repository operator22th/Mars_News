package com.example.yinshaofeng.di;

import com.example.yinshaofeng.ui.details.DetailsActivity;
import com.example.yinshaofeng.ui.main.MainActivity;
import com.example.yinshaofeng.di.main.MainModule;
import com.example.yinshaofeng.di.main.MainScope;
import com.example.yinshaofeng.di.main.fragment.FragmentMainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {
    @MainScope
    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentMainModule.class,

    })
    abstract MainActivity mainActivityInject();

    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivity();
}
