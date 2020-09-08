package com.softrunapps.rashapplication.storage.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Video> videos);

    @Query("DELETE FROM VIDEOS")
    void deleteAll();

    @Query("SELECT * FROM VIDEOS ORDER BY id ASC")
    LiveData<List<Video>> getAll();
}
