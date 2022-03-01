package com.nsa.stadiumfc.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;
import com.nsa.stadiumfc.repository.MainRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MainRepository repo;
    public MainViewModel(@NonNull Application application) {
        super(application);
        repo=new MainRepository(application);
    }

    public void saveUser(UserModel userModel) {
        repo.saveUser(userModel);
    }
    public LiveData<UserModel> getUser(){
        return repo.getUserData();
    }

    public void update(UserModel userModel) {
        repo.updateUser(userModel);
    }

    public void addAttendance(AttendanceModel attendanceModel) {
        repo.addAttendance(attendanceModel);
    }
    public LiveData<List<AttendanceModel>> getAttendance(){
        return repo.getAttendance();
    }
}
