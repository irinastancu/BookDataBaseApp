package com.example.tema3.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema3.R;
import com.example.tema3.fragments.FirstFragment;
import com.example.tema3.interfaces.OnBookItemClick;
import com.example.tema3.models.BookElement;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
ArrayList<BookElement> booksList;
public static OnBookItemClick onBookItemClick;

public BookAdapter(ArrayList<BookElement> booksList, OnBookItemClick onBookItemClick ){
    this.onBookItemClick = onBookItemClick;
    this.booksList = booksList;
}
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_cell, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);

        Log.e("BookAdapter", "onCreateViewHolder");

        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
       BookElement bookElement = booksList.get(position);
        holder.bind(bookElement);

        Log.e("BookAdapter", "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{


        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView description;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.current_title);
            bookAuthor = itemView.findViewById(R.id.current_author);
            description = itemView.findViewById(R.id.current_description);
        }

        void bind(BookElement bookElement){
            bookTitle.setText(bookElement.getBookTitle());
            bookAuthor.setText(bookElement.getBookAuthor());
            description.setText(bookElement.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   FirstFragment.bookClicked =bookElement;
                    if (BookAdapter.onBookItemClick != null)
                        BookAdapter.onBookItemClick.onClick(bookElement);
                }
            });
        }
    }
}
