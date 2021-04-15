package com.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemeDao {
    @Insert
    void addMeme(MemeDB memeDB);

    @Query("SELECT * FROM meme")
    List<MemeDB> getAllMemes();
}
