package com.nsa.stadiumfc.repository;

import android.os.AsyncTask;

import com.nsa.stadiumfc.db.UserDao;
import com.nsa.stadiumfc.models.UserModel;


public class UpdateUser extends AsyncTask<UserModel,Void,Void> {
    private UserDao userDao;
    public UpdateUser(UserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    protected Void doInBackground(UserModel... userModels) {
        userDao.deleteAll();
        userDao.insert(userModels[0]);
        return null;
    }
}
