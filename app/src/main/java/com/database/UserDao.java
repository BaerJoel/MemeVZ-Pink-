package com.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.FAIL;

@Dao
public interface UserDao {

    @Insert(onConflict = FAIL)
    void insert(UserDB userDB);

    @Delete
    void delete(UserDB userDB);

    @Query("SELECT * FROM user")
    List<UserDB> getAllUsers();

    @Query("SELECT * FROM user WHERE (LOWER(mail) LIKE LOWER(:mailOrUsername) OR LOWER(username) = LOWER(:mailOrUsername))")
    UserDB getUser(String mailOrUsername);

    @Query("SELECT * FROM user WHERE LOWER(id) LIKE LOWER(:user_id)")
    UserDB getUserByID(long user_id);

    @Update
    void updateUser(UserDB userDB);
}
