package com.example.yinshaofeng.database.saved.clicked;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yinshaofeng.model.ArticlesItem;
import com.example.yinshaofeng.model.clicked.ClickedArticle;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ClickedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void clickArticle(ClickedArticle clickedArticle);

    @Query("DELETE From clicked_table WHERE clicked_title =:articleTitle")
    void deleteClickedArticle(String articleTitle);

    @Query("SELECT * From clicked_table")
    Single<List<ClickedArticle>> getClickedArticles();

    @Query("SELECT COUNT(*) From clicked_table WHERE clicked_title =:name")
    Integer getSpecificClickedArticles(String name);

    @Query("SELECT COUNT(*) From clicked_table WHERE clicked_title =:name")
    Single<Integer> singleSpecificClickedArticles(String name);
}
