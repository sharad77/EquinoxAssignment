package com.example.equinoxassignment.Model;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table", indices = @Index(value = {"id"},unique = true))
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("dept_name")
    @ColumnInfo(name = "dept_name")
    private String deptName;

    public DataModel(int id, String name, String deptName) {
        this.id = id;
        this.name = name;
        this.deptName = deptName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeptName() {
        return deptName;
    }

}

