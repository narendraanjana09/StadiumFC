package com.nsa.stadiumfc.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.nsa.stadiumfc.extra.Constants;
import com.nsa.stadiumfc.models.AttendanceModel;
import com.nsa.stadiumfc.models.UserModel;


@androidx.room.Database(entities = {UserModel.class, AttendanceModel.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract UserDao noteDao();
    public abstract AttendanceDao attendanceDao();

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, Constants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}