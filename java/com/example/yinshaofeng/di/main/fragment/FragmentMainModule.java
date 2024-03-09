package com.example.yinshaofeng.di.main.fragment;

import com.example.yinshaofeng.di.main.fragment.category.CategoryModule;
import com.example.yinshaofeng.di.main.fragment.category.CategoryScope;
import com.example.yinshaofeng.di.main.fragment.clicked.ClickedModule;
import com.example.yinshaofeng.di.main.fragment.clicked.ClickedScope;
import com.example.yinshaofeng.di.main.fragment.home.HomeModule;
import com.example.yinshaofeng.di.main.fragment.home.HomeScope;
import com.example.yinshaofeng.di.main.fragment.saved.SavedModule;
import com.example.yinshaofeng.di.main.fragment.saved.SavedScope;
import com.example.yinshaofeng.di.main.fragment.search.SearchModule;
import com.example.yinshaofeng.di.main.fragment.search.SearchScope;
import com.example.yinshaofeng.ui.main.fragment.category.CategoryFragment;
import com.example.yinshaofeng.ui.main.fragment.clicked.ClickedFragment;
import com.example.yinshaofeng.ui.main.fragment.home.HomeFragment;
import com.example.yinshaofeng.ui.main.fragment.saved.SavedFragment;
import com.example.yinshaofeng.ui.main.fragment.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class FragmentMainModule {
    @HomeScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

    @SearchScope
    @ContributesAndroidInjector(modules = SearchModule.class)
    abstract SearchFragment searchFragment();

    @CategoryScope
    @ContributesAndroidInjector(modules = CategoryModule.class)
    abstract CategoryFragment categoryFragment();

    @SavedScope
    @ContributesAndroidInjector(modules = SavedModule.class)
    abstract SavedFragment savedFragment();

    @ClickedScope
    @ContributesAndroidInjector(modules = ClickedModule.class)
    abstract ClickedFragment clickedFragment();
}
