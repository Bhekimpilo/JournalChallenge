package com.example.iis.journalchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteClass {

    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 2;


    public static final String TABLE_NAME = "entries";
    public static String COLUMN_ID = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ENTRY = "entry";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_ENTRY + " TEXT" + ");" ;

    private String[] columns = {COLUMN_ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_ENTRY};

    SQLiteDatabase mSQLiteDatabase;
    SQLiteOpenHelper mSQLiteOpenHelper;
    Context mContext;

    public class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

    }
    public SQLiteClass(Context context){
        mContext = context;
    }

    public SQLiteClass open() {
        mSQLiteOpenHelper = new OpenHelper(mContext);
        mSQLiteDatabase = mSQLiteOpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mSQLiteOpenHelper.close();
    }

    public long write(String date, String title, String entry) {

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ENTRY, entry);

        return mSQLiteDatabase.insert(TABLE_NAME, null, cv);
    }



    public String getDate(long row) {
        Cursor que = mSQLiteDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + row, null, null, null, null);
        if (que != null) {
            que.moveToFirst();
            return que.getString(que.getColumnIndex(COLUMN_DATE));
        }
        return null;
    }

    public ArrayList<Arrange> list(String select, String criteria) {
        ArrayList<Arrange> myList = new ArrayList<>();
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, columns, select, null, null, null, criteria);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Arrange arrangeList = new Arrange();
                arrangeList.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                arrangeList.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                arrangeList.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                arrangeList.setEntry(cursor.getString(cursor.getColumnIndex(COLUMN_ENTRY)));

                myList.add(arrangeList);
            }
        }
        cursor.close();
        return myList;
    }

    public String getTitle(long row) {
        Cursor que = mSQLiteDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + row, null, null, null, null);
        if (que != null) {
            que.moveToFirst();
            return que.getString(que.getColumnIndex(COLUMN_TITLE));
        }
        return null;
    }

    public String getEntry(long row) {
        Cursor que = mSQLiteDatabase.query(TABLE_NAME, columns, COLUMN_ID + "=" + row, null, null, null, null);
        if (que != null) {
            que.moveToFirst();
            return que.getString(que.getColumnIndex(COLUMN_ENTRY));
        }
        return null;
    }

    public void editEntry(long myID, String entry) {
        ContentValues conVal = new ContentValues();
        conVal.put(COLUMN_ENTRY, entry);

        mSQLiteDatabase.update(TABLE_NAME, conVal, COLUMN_ID + "=" + myID, null);
    }

}
