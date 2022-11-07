package com.example.thi_30_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TinTucDao {
    MyDBHelper myDBHelper;
    SQLiteDatabase database;
    public TinTucDao(Context context){
        myDBHelper = new MyDBHelper(context);

    }
    public ArrayList<TinTuc> selectAll(){
        database = myDBHelper.getReadableDatabase();
        ArrayList<TinTuc> tinTucs = new ArrayList<>();
        String select = "SELECT * FROM TINTUC";
        Cursor c = database.rawQuery(select,null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                TinTuc tinTuc = new TinTuc();
                tinTuc.setId(c.getInt(0));
                tinTuc.setTitle(c.getString(1));
                tinTuc.setLink(c.getString(2));
                tinTuc.setPubDate(c.getString(3));
                tinTucs.add(tinTuc);
                c.moveToNext();
            }
        }
        return tinTucs;
    }

    public long addNew(TinTuc tinTuc){
        database = myDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",tinTuc.getId());
        contentValues.put("title",tinTuc.getTitle());
        contentValues.put("link",tinTuc.getLink());
        contentValues.put("linkDate",tinTuc.getPubDate());
        long check = database.insert("TINTUC", null,contentValues);
        return check;

    }
}
