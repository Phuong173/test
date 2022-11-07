package com.example.thi_30_10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    static final String db_name = "db_curd";
    SQLiteDatabase database;
    static final int db_verion =1;
    public MyDBHelper(Context context){
        super(context,db_name,null,db_verion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tableTinTuc = "CREATE TABLE TINTUC(id integer, title text, link text, linkDate text )";
        sqLiteDatabase.execSQL(tableTinTuc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
