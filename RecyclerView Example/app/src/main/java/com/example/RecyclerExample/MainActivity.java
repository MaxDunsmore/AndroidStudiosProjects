package com.example.RecyclerExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String[] string1;
    String[] string2;
    int[] images = {R.drawable.csharp, R.drawable.html, R.drawable.java, R.drawable.javascript,
            R.drawable.php, R.drawable.python, R.drawable.ruby, R.drawable.sql};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.myRecyclerView);


        string1 = getResources().getStringArray(R.array.coding_languages);
        string2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this,string1,string2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
