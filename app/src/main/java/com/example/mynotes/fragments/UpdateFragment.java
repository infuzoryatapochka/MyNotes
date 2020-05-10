package com.example.mynotes.fragments;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.database.DatabaseHandler;
import com.example.mynotes.models.Note;

public class UpdateFragment extends Fragment implements View.OnClickListener {
    DatabaseHandler database;
    Context context;
    EditText note_id_input;
    EditText title_input;
    EditText body_input;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_fragment, container, false);
        view.findViewById(R.id.submit_update).setOnClickListener(this);
        note_id_input = view.findViewById(R.id.note_id);
        title_input = view.findViewById(R.id.title);
        body_input = view.findViewById(R.id.body);

        return view;
    }

    @Override
    public void onClick(View v) {
        context = getActivity();
        database = new DatabaseHandler(context);
        int note_id = Integer.parseInt(String.valueOf(note_id_input.getText()));

        try {
            database.getNote(note_id);
        } catch (CursorIndexOutOfBoundsException e) {
            Toast.makeText(context, "This note id doesn't exist!", Toast.LENGTH_SHORT).show();
            return;
        }
        String title = title_input.getText().toString();
        String body = body_input.getText().toString();
        database.updateNote(new Note(note_id, title, body));

        note_id_input.setText("");
        title_input.setText("");
        body_input.setText("");
        Toast.makeText(context, "Note has been updated!", Toast.LENGTH_SHORT).show();

    }
}
