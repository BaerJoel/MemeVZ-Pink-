package com.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemeDao {
    @Insert
    void addMeme(MemeDB memeDB);

    @Query("SELECT * FROM meme")
    List<MemeDB> getAllMemes();

    @Query("SELECT (sum(likes) - sum(dislikes)) as score FROM meme WHERE uploader = :user_id")
    Long getUserScore(Long user_id);

    @Query("SELECT * FROM meme WHERE uploader = :user_id")
    List<MemeDB> getAllMemesFromUser(Long user_id);

    @Update
    void updateMeme(MemeDB meme);
}
