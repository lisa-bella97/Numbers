package com.example.numbers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final static int DEFAULT_NUMBERS_COUNT = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    NumberListFragment.newInstance(DEFAULT_NUMBERS_COUNT)).commit();
        }
    }
}
