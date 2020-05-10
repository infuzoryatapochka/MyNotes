package com.example.mynotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mynotes.models.Note;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "notes_manager";
    private static final String TABLE_NAME = "notes";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "note";

    private static final String[] COLS_ID_TITLE_NOTE = new String[] {KEY_ID,KEY_TITLE,KEY_BODY};


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"+", "
                + KEY_TITLE + " TEXT NOT NULL"+ ", "
                + KEY_BODY + " TEXT"
                + ")";

        Log.d(TAG,CREATE_NOTES_TABLE);

        db.execSQL(CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

        Log.d(TAG,DROP_TABLE);

        db.execSQL(DROP_TABLE);

        onCreate(db);

    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.title());
        values.put(KEY_BODY, note.body());


        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.title());
        values.put(KEY_BODY, note.body());


        db.update(TABLE_NAME,values, KEY_ID + "=?", new String[]{String.valueOf(note.id())});
        db.close();
    }

    public Note getNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME,COLS_ID_TITLE_NOTE,KEY_ID +"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(c != null){
            c.moveToFirst();
        }
        db.close();

        Log.d(TAG,"Get Note Result "+ c.getString(0)+","+c.getString(1)+","+c.getString(2));
        Note note = new Note(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2));
        return note;
    }

    public ArrayList<Note> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Note> noteList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME,COLS_ID_TITLE_NOTE,null,null,null,null,null);


        if(cursor!= null && cursor.moveToFirst()){

            do{
                Note note = new Note();
                note.id(Integer.parseInt(cursor.getString(0)));
                note.title(cursor.getString(1));
                note.body(cursor.getString(2));
                noteList.add(note);

            }while (cursor.moveToNext());


        }
        db.close();
        return noteList;

    }

}
