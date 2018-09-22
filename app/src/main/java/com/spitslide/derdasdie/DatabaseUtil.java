package com.spitslide.derdasdie;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private SQLiteDatabase database;
    private static final String SCORES_TABLE = "ScoresTable";
    private static final String NOUNS_TABLE = "NounsTable";


    public DatabaseUtil(Context context) {
        database = new DatabaseHelper(context).getWritableDatabase();
    }


    public void addScore(int score) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        database.insert(SCORES_TABLE, null, contentValues);
    }

    public Cursor getAllScores() {
        String[] columns = new String[]{"score"};
        return database.query(SCORES_TABLE, columns, null, null, null, null, null);
    }

    public int getLastScore() {
        String[] columns = new String[]{"score"};
        String orderBy = "score DESC";
        String limit = "1";
        Cursor cursor = database.query(SCORES_TABLE, columns, null, null, null, null, orderBy, limit);
        int lastScore;
        if (cursor != null) {
            cursor.moveToFirst();
            lastScore = cursor.getInt(0);
            cursor.close();
        } else {
            lastScore = 0;
        }
        return lastScore;
    }


    public void addAllNouns(List<Noun> nouns) {
        database.beginTransaction();
        database.delete(NOUNS_TABLE, null, null);
        ContentValues contentValues = new ContentValues();
        for (Noun noun : nouns) {
            contentValues.put("noun", noun.getNoun());
            contentValues.put("gender", noun.getGender());
            // TODO - both insert for the first time and update if later
            database.insert(NOUNS_TABLE, null, contentValues);
//            database.update(NOUNS_TABLE, contentValues, null, null);
//            int id = (int)database.insertWithOnConflict(NOUNS_TABLE, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
//            if (id == -1) {
//                database.update(NOUNS_TABLE, contentValues, null, null);
//            }
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public List<Noun> getAllNouns() {
        List<Noun> nouns = new ArrayList<>();
        String[] columns = new String[]{"noun", "gender"};
        Cursor cursor = database.query(NOUNS_TABLE, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Noun noun = new Noun(cursor.getString(0), cursor.getString(1));
            nouns.add(noun);
        }
        cursor.close();
        return nouns;
    }

    public Noun getFirstNoun(){
        String[] columns = new String[]{"noun", "gender"};
        String limit = "1";
        Cursor cursor = database.query(NOUNS_TABLE, columns, null, null, null, null, null, limit);
        Noun noun;
        if (cursor != null) {
            cursor.moveToFirst();
            noun = new Noun(cursor.getString(0), cursor.getString(1));
            cursor.close();
        } else {
            // TODO - if no more words
            noun = new Noun("", "");
        }
        return noun;
    }




    class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Database";
        private static final int DATABASE_VERSION = 1;
        private static final String CREATE_SCORES_TABLE = "CREATE TABLE IF NOT EXISTS " + SCORES_TABLE + " (_id INTEGER PRIMARY KEY, score INTEGER);";
        private static final String CREATE_NOUNS_TABLE = "CREATE TABLE IF NOT EXISTS " + NOUNS_TABLE + " (_id INTEGER PRIMARY KEY, noun TEXT, gender TEXT);";

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_SCORES_TABLE);
            sqLiteDatabase.execSQL(CREATE_NOUNS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
