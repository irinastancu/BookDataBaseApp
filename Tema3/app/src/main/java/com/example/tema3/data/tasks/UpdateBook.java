package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.BookDataBase;
import com.example.tema3.data.BookRepository;
import com.example.tema3.models.dbEntities.Book;

public class UpdateBook extends AsyncTask<Book, Void, Void> {

    private BookDataBase bookDataBase;
    private BookRepository.OnSuccesListener listener;

    public UpdateBook(BookDataBase bookDataBase,BookRepository.OnSuccesListener listener ){
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }


    @Override
    protected Void doInBackground(Book... books) {
       bookDataBase.bookDAO().update(books[0]);
       return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
