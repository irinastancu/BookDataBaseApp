package com.example.tema3.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema3.R;
import com.example.tema3.adapters.BookAdapter;
import com.example.tema3.data.BookRepository;
import com.example.tema3.interfaces.ActivityFragmentCommunication;
import com.example.tema3.interfaces.OnBookItemClick;
import com.example.tema3.models.BookElement;
import com.example.tema3.models.dbEntities.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button button;
    private EditText editTextTitle;
    private EditText editTextAuthor;
    private EditText editTextDescription;
    private BookRepository bookRepository = new BookRepository();
    private BookAdapter bookAdapter;

    private RecyclerView recyclerView;
    private ArrayList<BookElement> booksList;
    private ActivityFragmentCommunication activityFragmentCommunication;

    public static BookElement bookClicked = null;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = view.findViewById(R.id.add_btn);
        editTextTitle = view.findViewById((R.id.book_title));
        editTextAuthor = view.findViewById(R.id.book_author);
        editTextDescription = view.findViewById(R.id.book_description);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBook();
            }
        });
        getBooks();


    }

    public void insertBook() {

        String title = editTextTitle.getText().toString();
        if (title == null) {
            editTextTitle.setError(getString(R.string.error_required));
        }

        String author = editTextAuthor.getText().toString();
        if (author == null) {
            editTextAuthor.setError(getString(R.string.error_required));
        }

        String description = editTextDescription.getText().toString();
        if (description == null) {
            editTextDescription.setError(getString(R.string.error_required));
        }

        Book book = new Book(title, author, description);
        bookRepository.insertBook(book, new BookRepository.OnSuccesListener() {
            @Override
            public void onSuccess() {
                booksList.add(book.convert());
                bookAdapter.notifyItemChanged(booksList.size() - 1);
                bookAdapter.notifyDataSetChanged();

                Toast.makeText(getContext(), "Success.", Toast.LENGTH_SHORT).show();

            }
        });
        editTextTitle.setText("");
        editTextAuthor.setText("");
        editTextDescription.setText("");

    }

    public void getBooks() {

        bookRepository.getAllBook(new BookRepository.OnGetToDosListener() {
            @Override
            public void onSuccess(List<Book> items) {
                booksList.clear();
                for (Book b : items) {
                    booksList.add(b.convert());

                }
                bookAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        booksList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.books_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        booksList.clear();
        bookAdapter = new BookAdapter(booksList, new OnBookItemClick() {
            @Override
            public void onClick(BookElement book) {
                if (activityFragmentCommunication != null) {
                    activityFragmentCommunication.replaceWithAboutBookFragment();

                }
            }
        });
        recyclerView.setAdapter(bookAdapter);
        return view;


    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        if (context instanceof ActivityFragmentCommunication) {
            activityFragmentCommunication = (ActivityFragmentCommunication) context;
        }
    }
}