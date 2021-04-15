package com.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "meme")
public class MemeDB implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "blob")
    private String blob;

    @ColumnInfo(name = "likes")
    private Long likes;

    @ColumnInfo(name = "dislikes")
    private Long dislikes;

    @ColumnInfo(name = "uploader")
    private Long uploader_id;

    public Long getUploader_id() {
        return uploader_id;
    }

    public void setUploader_id(Long uploader_id) {
        this.uploader_id = uploader_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

}
