package com.android.roomdb.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userData")
    List<UserEntity> getAll();

    @Query("SELECT * FROM userData WHERE email_address LIKE :emailaddress AND  password Like :password")
    UserEntity loginData(String emailaddress, String password);

    @Query("SELECT * FROM userData WHERE email_address= :emailAddress and password= :password")
    List<UserEntity> loginCheck(String emailAddress, String password);

    @Query("SELECT password FROM userData WHERE password= :password")
    String loginCheckPwd(String password);

    @Query("SELECT * FROM userData WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int userIds);

    @Query("SELECT * FROM userData WHERE uid = :userId")
    UserEntity loadById(int userId);

    @Query("SELECT * FROM userData WHERE name LIKE :name")
    UserEntity findByName(String name);

    @Insert
    void insertUser(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}