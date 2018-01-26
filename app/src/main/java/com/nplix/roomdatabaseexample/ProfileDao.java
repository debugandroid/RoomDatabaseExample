package com.nplix.roomdatabaseexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.provider.ContactsContract;

import java.util.List;

/**
 * Created by PK on 1/23/2018.
 */
@Dao
public interface ProfileDao {
    @Query("SELECT * FROM ProfileEntity")
    List<ProfileEntity> getAll();

    @Query("SELECT * FROM ProfileEntity WHERE id IN (:userIds)")
    List<ProfileEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM ProfileEntity WHERE Name LIKE :name LIMIT 1")
    ProfileEntity findByName(String name);

    @Insert
    void insertAll(ProfileEntity... users);

    @Delete
    void delete(ProfileEntity user);
}