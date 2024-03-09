package com.example.yinshaofeng.database.saved;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yinshaofeng.model.saved.SavedArticle;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticle(SavedArticle savedArticle);

    @Query("DELETE From saved_table WHERE saved_title =:articleTitle")
    void deleteSavedArticle(String articleTitle);

    @Query("SELECT * From saved_table")
    Single<List<SavedArticle>> getSavedArticles();

    @Query("SELECT COUNT(*) From saved_table WHERE saved_title =:name")
    Integer getSpecificSavedArticles(String name);

    @Query("SELECT COUNT(*) From saved_table WHERE saved_title =:name")
    Single<Integer> singleSpecificSavedArticles(String name);
}
