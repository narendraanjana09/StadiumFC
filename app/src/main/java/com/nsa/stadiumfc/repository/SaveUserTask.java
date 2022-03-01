package com.nsa.stadiumfc.repository;

import android.os.AsyncTask;

import com.nsa.stadiumfc.db.UserDao;
import com.nsa.stadiumfc.models.UserModel;

public class SaveUserTask extends AsyncTask<UserModel,Void,Void> {
    private UserDao userDao;
    public SaveUserTask(UserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    protected Void doInBackground(UserModel... noteModels) {
        userDao.insert(noteModels[0]);
        return null;
    }
}
