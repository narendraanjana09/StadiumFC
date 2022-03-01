package com.nsa.stadiumfc.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;

import java.util.List;

@Dao
public interface AttendanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AttendanceModel attendanceModel);

    @Query("SELECT * FROM attendance_table")
    LiveData<List<AttendanceModel>> getAttendance();

}