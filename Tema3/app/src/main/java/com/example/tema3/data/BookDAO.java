package com.example.tema3.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tema3.models.dbEntities.Book;

import java.util.List;

@Dao
public interface BookDAO {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Insert
    void insertAll(Book... toDoItem);

    @Insert
    void insertBook(Book toDoItem);

    @Delete
    void delete(Book toDoItem);

    @Query("DELETE FROM book WHERE title = :itemTitle")
    void deleteByTitle(String itemTitle);

    @Update
    void update(Book book);
}
