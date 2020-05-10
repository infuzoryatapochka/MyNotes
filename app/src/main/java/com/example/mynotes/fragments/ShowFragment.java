package com.example.mynotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.ListFragment;

import com.example.mynotes.R;
import com.example.mynotes.adapters.NotesIndexAdapter;
import com.example.mynotes.database.DatabaseHandler;
import com.example.mynotes.models.Note;

import java.util.ArrayList;


public class ShowFragment extends ListFragment {
    private ArrayList<Note> notes;
    DatabaseHandler database;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_fragment, container, false);
        Context context = getActivity();
        database = new DatabaseHandler(context);
        notes = database.getAllNotes();

        if(notes.size() == 0) {
            for(int i = 0; i < 10; i++) {
                database.addNote(new Note("Note " +  i, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eu consectetur nisi, a"));
            }
            notes = database.getAllNotes();
        }

        setListAdapter(new NotesIndexAdapter(context, notes));

        return view;
    }

}
