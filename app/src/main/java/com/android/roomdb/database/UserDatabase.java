package com.android.roomdb.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.roomdb.activity.MyApplication;

@Database(entities = {UserEntity.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase{
    public abstract UserDao userDao();

    private static UserDatabase INSTANCE;

    public static UserDatabase getDbInstance()
    {
        if(INSTANCE ==null)
        {
             INSTANCE = Room.databaseBuilder(MyApplication.getInstance(),UserDatabase.class,"USER_DATA")
                     .allowMainThreadQueries()
                     .build();
        }
        return INSTANCE;
    }
}