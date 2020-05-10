package com.example.mynotes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.mynotes.adapters.NotesPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragment_manager;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        fragment_manager = getSupportFragmentManager();
        pager.setAdapter(new NotesPagerAdapter(fragment_manager));
    }

}
