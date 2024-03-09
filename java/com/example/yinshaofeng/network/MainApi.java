package com.example.yinshaofeng.network;

import com.example.yinshaofeng.model.Response;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {
    @GET("svc/news/queryNewsList")
    Flowable<Response> getTopHead(
            @Query("page") int page,
            @Query("size") int size,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate,
            @Query("words") String words,
            @Query("categories") String categories
    );
}
