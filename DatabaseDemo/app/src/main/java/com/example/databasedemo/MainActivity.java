package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("newUsers",MODE_PRIVATE,null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(4), id INTEGER PRIMARY KEY )");
            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Dave', 17)");
            sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Steve', 14)");
            sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE id = 1");
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers",null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while (c != null){
                Log.i("UserResults - name",c.getString(nameIndex));
                Log.i("UserResults - age",Integer.toString(c.getInt(ageIndex)));
                Log.i("UserResults - id",Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
