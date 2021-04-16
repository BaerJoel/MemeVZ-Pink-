package com.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface RankingDao {
    @Insert
    void addRanking(RankingDB rankingDB);


}
