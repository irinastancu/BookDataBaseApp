package com.example.tema3;

import android.app.Application;

import androidx.room.Room;

import com.example.tema3.data.BookDataBase;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    private static BookDataBase bookDataBase;

    private final String bookDataBaseName = "BookDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setUpDataBase();

    }
    private void setUpDataBase(){
        bookDataBase = Room.databaseBuilder(
                getApplicationContext(),
                BookDataBase.class,
                bookDataBaseName)
                .addMigrations(BookDataBase.MIGRATION_1_2)
                // .fallbackToDestructiveMigration()
                .build();
    }
    public static BookDataBase getBookDataBase() {
        return bookDataBase;
    }

    public static ApplicationController getInstance() {
        return instance;
    }
}
