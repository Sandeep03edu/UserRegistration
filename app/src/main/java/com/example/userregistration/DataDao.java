package com.example.userregistration;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insert(Data data);

    @Update
    void update(Data data);

    @Delete
    void delete(Data data);

    @Query("DELETE FROM data_table")
    void deleteAllDatas();

    @Query("SELECT * FROM data_table ORDER BY id DESC")
    LiveData<List<Data>> getAllDatas();
}
