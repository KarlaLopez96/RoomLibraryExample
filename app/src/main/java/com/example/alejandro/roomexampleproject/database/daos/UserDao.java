package com.example.alejandro.roomexampleproject.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.alejandro.roomexampleproject.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE (:name) LIMIT 1")
    User findByName(String name);

    @Query("SELECT * FROM user WHERE first_name LIKE (:first) AND last_name LIKE (:last) LIMIT 1")
    User findByFullName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}