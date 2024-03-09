package com.example.yinshaofeng.model.clicked;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.yinshaofeng.utils.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = Constants.CLICKED_TABLE_NAME)
public class ClickedArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    public ClickedArticle() {
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "urlToImage")
    @SerializedName("image")
    private String urlToImage = "";

    @ColumnInfo(name = "urlToVideo")
    @SerializedName("video")
    private String urlToVideo = "";

    @ColumnInfo(name = "publishTime")
    @SerializedName("publishTime")
    private String publishTime = "";

    @ColumnInfo(name = "clicked_title")
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

    public String getUrl() {
        if (url == null)
            return "";
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToVideo() {
        if (urlToVideo == null)
            return "";
        return urlToVideo;
    }
    public void setUrlToVideo(String urlToVideo) {
        this.urlToVideo = urlToVideo;
    }
    public String getUrlToImage() {
        if (urlToImage == null)
            return "";
        return urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublisher() {
        if (publisher == null)
            return "";
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        if (content == null)
            return "";
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    @Ignore
    public ClickedArticle(String urlToImage, String urlToVideo, String publishTime, String title, String content, String publisher, String url) {
        if(content != null){
            content = content.replace(" ", "\t");
        }
        this.urlToImage = urlToImage;
        this.urlToVideo = urlToVideo;
        this.publishTime = publishTime;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.url = url;
    }

    protected ClickedArticle(Parcel in) {
        uid = in.readInt();
        urlToImage = in.readString();
        urlToVideo = in.readString();
        publishTime = in.readString();
        title = in.readString();
        content = in.readString();
        publisher = in.readString();
        url = in.readString();
    }

}