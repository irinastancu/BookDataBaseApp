package com.example.tema3.data;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.tema3.models.dbEntities.Book;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Book.class},version = 1)

public abstract class BookDataBase extends RoomDatabase {

    public abstract BookDAO bookDAO();
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
           // database.execSQL("ALTER TABLE ToDoItem ADD COLUMN description TEXT");
        }
    };
}
