package com.phacsin.carchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GD on 9/14/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context){
        super(context,"carchase",null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        String TABLE_ACTIVE_FILTER = "CREATE TABLE active_filter(filter_name TEXT,filter_value TEXT)";
        String TABLE_INACTIVE_FILTER = "CREATE TABLE inactive_filter(filter_name TEXT)";

        db.execSQL(TABLE_ACTIVE_FILTER);
        db.execSQL(TABLE_INACTIVE_FILTER);
        db.execSQL("INSERT INTO inactive_filter(filter_name) VALUES('Price')");
        db.execSQL("INSERT INTO inactive_filter(filter_name) VALUES('Year')");
        db.execSQL("INSERT INTO inactive_filter(filter_name) VALUES('Style')");
        db.execSQL("INSERT INTO inactive_filter(filter_name) VALUES('Colour')");
        db.execSQL("INSERT INTO inactive_filter(filter_name) VALUES('Fuel')");
    }
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS active_filter");
        database.execSQL("DROP TABLE IF EXISTS inactive_filter");
        onCreate(database);
    }

    public void insertActiveFilter (String name, String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("filter_name", name);
        contentValues.put("filter_value", value);
        db.insert("active_filter", null, contentValues);
    }

    public void insertInactiveFilter (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("filter_name", name);
        db.insert("inactive_filter", null, contentValues);
    }

    public void updateActiveFilter (String name, String value)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE active_filter SET filter_value='"+value+"' WHERE filter_name='"+name+"'";
            db.execSQL(query);
        }catch (SQLiteException e) {
            Log.d("sqlite",e.toString());
        }
    }


    public void removeActiveFilter (String name)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM active_filter WHERE filter_name='"+name+"'";
            db.execSQL(query);
        }catch (SQLiteException e) {
            Log.d("sqlite",e.toString());
        }
    }

    public void removeInactiveFilter (String name)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM inactive_filter WHERE filter_name='"+name+"'";
            db.execSQL(query);
        }catch (SQLiteException e) {
            Log.d("sqlite",e.toString());
        }
    }

    public List<FilterDetails> getAllActiveFilters()
    {
        List<FilterDetails> list = new ArrayList<FilterDetails>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * from active_filter", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            FilterDetails details = new FilterDetails();
            details.name=res.getString(res.getColumnIndex("filter_name"));
            details.value=res.getString(res.getColumnIndex("filter_value"));
            list.add(details);
            res.moveToNext();
        }
        return list;
    }

    public List<String> getAllInactiveFilters()
    {
        List<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * from inactive_filter", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex("filter_name")));
            res.moveToNext();
        }
        return array_list;
    }

    public int numberOfRows(String table_name){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,table_name);
        return numRows;
    }

}
