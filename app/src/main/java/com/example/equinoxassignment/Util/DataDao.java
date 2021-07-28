package com.example.equinoxassignment.Util;

import com.example.equinoxassignment.Model.DataModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DataModel> dataModelList);

    @Query("SELECT * FROM data_table")
    LiveData<List<DataModel>> getAllData();

    @Query("DELETE FROM data_table")
    void deleteAll();
}
