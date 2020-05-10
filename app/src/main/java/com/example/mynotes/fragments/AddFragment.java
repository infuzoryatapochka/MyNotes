package com.example.mynotes.fragments;

import android.content.Context;
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

public class AddFragment extends Fragment implements View.OnClickListener {
    EditText title_input;
    EditText body_input;
    Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        title_input = view.findViewById(R.id.title);
        body_input = view.findViewById(R.id.body);

        view.findViewById(R.id.submit_add).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String title = title_input.getText().toString();
        String body = body_input.getText().toString();
        context = getActivity();

        new DatabaseHandler(context).addNote(new Note(title, body));
        title_input.setText("");
        body_input.setText("");
        Toast.makeText(context, "Note has been created!", Toast.LENGTH_SHORT).show();

    }
}
