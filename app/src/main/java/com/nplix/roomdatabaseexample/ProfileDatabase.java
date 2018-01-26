package com.nplix.roomdatabaseexample;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by PK on 1/21/2018.
 */
@Database(entities = {ProfileEntity.class},version = 2)
public abstract class ProfileDatabase extends RoomDatabase{
    public abstract ProfileDao profileDao();
}
