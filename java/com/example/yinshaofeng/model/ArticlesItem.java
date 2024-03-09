package com.example.yinshaofeng.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.yinshaofeng.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(tableName = Constants.ARTICLES_TABLE_NAME)

public class ArticlesItem implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArticlesItem() {
    }


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "urlToImage")
    @SerializedName("image")
    private String urlToImage = "";

    @SerializedName("video")
    @ColumnInfo(name = "urlToVideo")
    private String urlToVideo = "";

    @ColumnInfo(name = "publishTime")
    @SerializedName("publishTime")
    private String publishTime = "";


    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title = "";

    @ColumnInfo(name = "content")
    @SerializedName("content")
    private String content = "";

    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    private String publisher = "";

    @ColumnInfo(name = "url")
    @SerializedName("url")
    private String url = "";



    public static final DiffUtil.ItemCallback<ArticlesItem> CALLBACK = new DiffUtil.ItemCallback<ArticlesItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticlesItem articlesItem, @NonNull ArticlesItem articlesItem2) {
            return Objects.equals(articlesItem.getTitle(), articlesItem2.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlesItem articlesItem, @NonNull ArticlesItem articlesItem2) {
            return true;
        }
    };

    protected ArticlesItem(Parcel in) {
        uid = in.readInt();
        urlToImage = in.readString();
        urlToVideo = in.readString();
        publishTime = in.readString();
        title = in.readString();
        content = in.readString();
        publisher = in.readString();
        url = in.readString();
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToVideo() {
        return urlToVideo;
    }
    public void setUrlToVideo(String urlToVideo) {
        this.urlToVideo = urlToVideo;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}