package com.example.tema3.data.tasks;

import android.os.AsyncTask;

import com.example.tema3.data.BookDataBase;
import com.example.tema3.data.BookRepository;

public class DeleteByTitle extends AsyncTask<String, Void, Void> {

    private BookDataBase bookDataBase;
    private BookRepository.OnSuccesListener listener;

    public DeleteByTitle(BookDataBase bookDataBase, BookRepository.OnSuccesListener listener){
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(String... titles) {
        bookDataBase.bookDAO().deleteByTitle(titles[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
