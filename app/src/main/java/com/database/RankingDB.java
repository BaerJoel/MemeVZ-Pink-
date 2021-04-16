package com.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ranking")
public class RankingDB implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long ranking_id;

    @ColumnInfo(name = "user_id")
    private Long user_id;

    @ColumnInfo(name = "meme_id")
    private Long meme_id;

    public Long getRanking_id() {
        return ranking_id;
    }

    public void setRanking_id(Long ranking_id) {
        this.ranking_id = ranking_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getMeme_id() {
        return meme_id;
    }

    public void setMeme_id(Long meme_id) {
        this.meme_id = meme_id;
    }
}
