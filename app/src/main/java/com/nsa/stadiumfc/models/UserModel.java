package com.nsa.stadiumfc.models;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.nsa.stadiumfc.extra.Constants;

@Entity(tableName = Constants.TABLE_NAME)
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String dob;
    private String email;

    public UserModel(int id, String name, String dob, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
