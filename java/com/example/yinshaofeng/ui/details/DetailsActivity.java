package com.example.yinshaofeng.ui.details;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.yinshaofeng.R;
import com.example.yinshaofeng.ViewModelProviderFactory;
import com.example.yinshaofeng.databinding.ActivityDetailsBinding;
import com.example.yinshaofeng.model.saved.SavedArticle;
import com.example.yinshaofeng.ui.ScreenRotateUtils;
import com.example.yinshaofeng.ui.details.fragment.WebViewFragment;

import javax.inject.Inject;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import dagger.android.support.DaggerAppCompatActivity;

public class DetailsActivity extends DaggerAppCompatActivity implements ScreenRotateUtils.OrientationChangeListener{
    private DetailsViewModel viewModel;
    private ActivityDetailsBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private JzvdStd mJzvdStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(DetailsViewModel.class);
        Intent intent = getIntent();
        SavedArticle articlesItem = (SavedArticle) intent.getSerializableExtra("article");

        binding.setViewModel(viewModel);
        binding.setReqManager(requestManager);
        binding.setArticle(articlesItem);
        viewModel.checkIfArticleExist(articlesItem.getTitle());
        observeObservers(articlesItem.getUrl(), articlesItem.getTitle());

        mJzvdStd = (JzvdStd) findViewById(R.id.jz_video);
        mJzvdStd.setUp(articlesItem.getUrlToVideo(),"新闻视频", JzvdStd.SCREEN_NORMAL);
        Glide.with(this)
                .load("http://robertinventor.online/booklets/TopNews.jpg")
                .into(mJzvdStd.posterImageView);
        mJzvdStd.startPreloading(); //开始预加载，加载完等待播放
        mJzvdStd.startVideoAfterPreloading(); //如果预加载完会开始播放，如果未加载则开始加载
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;// 进入全屏后是竖屏
    }
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
    @Override
    public void orientationChange(int orientation) {
        if (Jzvd.CURRENT_JZVD != null
                && (mJzvdStd.state == Jzvd.STATE_PLAYING || mJzvdStd.state == Jzvd.STATE_PAUSE)
                && mJzvdStd.screen != Jzvd.SCREEN_TINY) {
            if (orientation >= 45 && orientation <= 315 && mJzvdStd.screen == Jzvd.SCREEN_NORMAL) {
                changeScreenFullLandscape(ScreenRotateUtils.orientationDirection);
            } else if (((orientation >= 0 && orientation < 45) || orientation > 315) && mJzvdStd.screen == Jzvd.SCREEN_FULLSCREEN) {
                changeScrenNormal();
            }
        }
    }


    /**
     * 竖屏并退出全屏
     */
    private void changeScrenNormal() {
        if (mJzvdStd != null && mJzvdStd.screen == Jzvd.SCREEN_FULLSCREEN) {
            mJzvdStd.autoQuitFullscreen();
        }
    }

    /**
     * 横屏
     */
    private void changeScreenFullLandscape(float x) {
        //从竖屏状态进入横屏
        if (mJzvdStd != null && mJzvdStd.screen != Jzvd.SCREEN_FULLSCREEN) {
            if ((System.currentTimeMillis() - Jzvd.lastAutoFullscreenTime) > 2000) {
                mJzvdStd.autoFullscreen(x);
                Jzvd.lastAutoFullscreenTime = System.currentTimeMillis();
            }
        }
    }
    private void observeObservers(String link, String title) {
        viewModel.checkIfArticleExist(title).observe(this, isExist-> {
            if(isExist)
                binding.saveArticle.setImageResource(R.drawable.ic_saved_blue);
            else
                binding.saveArticle.setImageResource(R.drawable.ic_saved_open);
        });

        viewModel.getCloseArticle().observe(this, close -> {
            if (close)
                finish();
        });

        viewModel.getOpenArticleWebView().observe(this, openWebView -> {
            if (openWebView)
                openWebView(link);

        });
    }

    private void openWebView(String link) {
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new WebViewFragment(link))
                .addToBackStack(null)
                .commit();
    }
}
