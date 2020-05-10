package com.example.mynotes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mynotes.R;
import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class NotesIndexAdapter extends BaseAdapter {

    private ArrayList<Note> notes;
    private Context context;
    private LayoutInflater layoutInflater;
    private View view;

    public NotesIndexAdapter(Context context, ArrayList<Note> notes) {
        this.notes = notes;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView == null ? layoutInflater.inflate(R.layout.note, null) : convertView;

        return assign_variables(this.getItem(position));
    }

    private View assign_variables(Note note) {
        TextView id = view.findViewById(R.id.note_id);
        id.setText(String.valueOf(note.id()));

        TextView title = view.findViewById(R.id.title);
        title.setText(note.title());

        TextView body = view.findViewById(R.id.body);
        body.setText(note.body());

        return view;
    }
}
