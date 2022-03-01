package com.nsa.stadiumfc.repository;

import android.os.AsyncTask;

import com.nsa.stadiumfc.db.AttendanceDao;
import com.nsa.stadiumfc.db.UserDao;
import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;

public class AddAttendance extends AsyncTask<AttendanceModel,Void,Void> {
    private AttendanceDao attendanceDao;
    public AddAttendance( AttendanceDao attendanceDao) {
        this.attendanceDao=attendanceDao;
    }

    @Override
    protected Void doInBackground(AttendanceModel... attendanceModels) {
        attendanceDao.insert(attendanceModels[0]);
        return null;
    }
}
