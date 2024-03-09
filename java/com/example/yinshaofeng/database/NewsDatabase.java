package com.example.yinshaofeng.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.yinshaofeng.database.saved.clicked.ClickedDao;
import com.example.yinshaofeng.database.saved.SavedDao;
import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.model.clicked.ClickedArticle;
import com.example.yinshaofeng.model.saved.SavedArticle;

@Database(entities = {ArticlesItem.class , SavedArticle.class, ClickedArticle.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract SavedDao savedDao();
    public abstract ClickedDao clickedDao();
}
