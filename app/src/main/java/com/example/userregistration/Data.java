package com.example.userregistration;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table")
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private long phoneNum;

    public Data(String name, long phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNum() {
        return phoneNum;
    }
}
