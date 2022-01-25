package com.nimbasoft.nrms.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nimbasoft.nrms.Models.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
