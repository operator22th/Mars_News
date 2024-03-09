package com.example.yinshaofeng.model.converter;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.model.clicked.ClickedArticle;
import com.example.yinshaofeng.model.saved.SavedArticle;

public class ArticleConverter {
    public static SavedArticle convertToSavedArticle(ArticlesItem articlesItem) {
        SavedArticle savedArticle = new SavedArticle();
        if (articlesItem == null) {
            return savedArticle;
        }
        savedArticle.setUrlToImage(articlesItem.getUrlToImage());
        savedArticle.setUrlToVideo(articlesItem.getUrlToVideo());
        savedArticle.setPublishTime(articlesItem.getPublishTime());
        savedArticle.setTitle(articlesItem.getTitle());
        savedArticle.setContent(articlesItem.getContent());
        savedArticle.setPublisher(articlesItem.getPublisher());
        savedArticle.setUrl(articlesItem.getUrl());

        return savedArticle;
    }

    public static SavedArticle convertToSavedArticle(ClickedArticle articlesItem) {
        SavedArticle savedArticle = new SavedArticle();
        if (articlesItem == null) {
            return savedArticle;
        }
        savedArticle.setUrlToImage(articlesItem.getUrlToImage());
        savedArticle.setUrlToVideo(articlesItem.getUrlToVideo());
        savedArticle.setPublishTime(articlesItem.getPublishTime());
        savedArticle.setTitle(articlesItem.getTitle());
        savedArticle.setContent(articlesItem.getContent());
        savedArticle.setPublisher(articlesItem.getPublisher());
        savedArticle.setUrl(articlesItem.getUrl());

        return savedArticle;
    }

    public static ClickedArticle convertToClickedArticle(ArticlesItem articlesItem) {
        ClickedArticle clickedArticle = new ClickedArticle();
        if (articlesItem == null) {
            return clickedArticle;
        }
        clickedArticle.setUrlToImage(articlesItem.getUrlToImage());
        clickedArticle.setUrlToVideo(articlesItem.getUrlToVideo());
        clickedArticle.setPublishTime(articlesItem.getPublishTime());
        clickedArticle.setTitle(articlesItem.getTitle());
        clickedArticle.setContent(articlesItem.getContent());
        clickedArticle.setPublisher(articlesItem.getPublisher());
        clickedArticle.setUrl(articlesItem.getUrl());

        return clickedArticle;
    }
}
