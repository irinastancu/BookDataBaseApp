package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.BookDataBase;
import com.example.tema3.data.BookRepository;
import com.example.tema3.models.dbEntities.Book;

import java.util.List;

public class GetAllBooks extends AsyncTask<Void, Void, List<Book>> {
    private BookDataBase bookDataBase;
    private BookRepository.OnGetToDosListener listener;

    public GetAllBooks(BookDataBase bookDataBase, BookRepository.OnGetToDosListener listener){
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }
    @Override
    protected List<Book> doInBackground(Void... voids) {
        return bookDataBase.bookDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<Book> items) {
        super.onPostExecute(items);
        listener.onSuccess(items);
    }
}
