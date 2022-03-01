package com.nsa.stadiumfc.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.nsa.stadiumfc.db.AttendanceDao;
import com.nsa.stadiumfc.db.Database;
import com.nsa.stadiumfc.db.UserDao;
import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;

import java.util.List;

public class MainRepository {
    private Application application;
    private UserDao userDao;
    private AttendanceDao attendanceDao;

    public MainRepository(Application application) {
        this.application = application;
        userDao= Database.getInstance(application).noteDao();
        attendanceDao= Database.getInstance(application).attendanceDao();
    }

    public void saveUser(UserModel userModel) {
        new SaveUserTask(userDao).execute(userModel);
    }
    public LiveData<UserModel> getUserData(){
        return userDao.getUser();
    }

    public void updateUser(UserModel userModel) {
        new UpdateUser(userDao).execute(userModel);
    }

    public void addAttendance(AttendanceModel attendanceModel) {
        new AddAttendance(attendanceDao).execute(attendanceModel);
    }

    public LiveData<List<AttendanceModel>> getAttendance() {
        return attendanceDao.getAttendance();
    }
}
