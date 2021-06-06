package com.example.tema3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tema3.R;
import com.example.tema3.fragments.FirstFragment;
import com.example.tema3.fragments.SecondFragment;
import com.example.tema3.interfaces.ActivityFragmentCommunication;

public class MainActivity extends AppCompatActivity implements ActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                FirstFragment.newInstance(" ", " ")).commit();

    }

    @Override
    public void replaceWithAboutBookFragment() {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,  SecondFragment.newInstance("",""), "SecondFragmentTag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}