package com.example.tema3.data;

import com.example.tema3.ApplicationController;
import com.example.tema3.data.tasks.DeleteBook;
import com.example.tema3.data.tasks.DeleteByTitle;
import com.example.tema3.data.tasks.GetAllBooks;
import com.example.tema3.data.tasks.InsertBook;
import com.example.tema3.data.tasks.UpdateBook;
import com.example.tema3.models.dbEntities.Book;

import java.util.List;

public class BookRepository {
    public static interface OnSuccesListener {
        void onSuccess();
    }

    public static interface OnGetToDosListener {
        void onSuccess(List<Book> items);
    }
    private BookDataBase bookDataBase;
    public BookRepository() {
        bookDataBase = ApplicationController.getBookDataBase();
    }

    public void insertBook(Book toDoItem, OnSuccesListener listener) {
        new InsertBook(bookDataBase, listener).execute(toDoItem);
    }

    public void getAllBook(OnGetToDosListener listener) {
        new GetAllBooks(bookDataBase, listener).execute();
    }

    public void updateBook(Book toDoItem, OnSuccesListener listener) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new UpdateToDoTask(toDoDataBase, listener).execute(toDoItem);
//            }
//        }, 5000);
        new UpdateBook(bookDataBase, listener).execute(toDoItem);
    }

    public void deleteBook(Book toDoItem, OnSuccesListener listener) {
        new DeleteBook(bookDataBase, listener).execute(toDoItem);
    }
    public void deleteByTitleToDo(String toDoItem, OnSuccesListener listener) {
        new DeleteByTitle(bookDataBase, listener).execute(toDoItem);
    }
}
