package com.nsa.stadiumfc.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.nsa.stadiumfc.extra.Constants;

@Entity(tableName = Constants.ATT_TABLE_NAME)
public class AttendanceModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String date;
    private String time;
    private String imageUri;

    public AttendanceModel(int id, String name, String date, String time, String imageUri) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.imageUri = imageUri;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
