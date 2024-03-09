package com.example.yinshaofeng.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yinshaofeng.model.ArticlesItem;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<ArticlesItem> articlesList);

    @Query("SELECT * From articles_table LIMIT :size OFFSET :offset ")
    Flowable<List<ArticlesItem>> getArticles(int size, int offset);

    @Query("DELETE From articles_table")
    void deleteArticles();
}