package com.example.rbrazuk.comics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static List<Comic> comicsArray;
    public static ArrayList<Comic> comicsArrayList;
    Button showListButton;
    Intent intent;
    Spinner sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        intent = new Intent(MainActivity.this,ListActivity.class);

        sortSpinner = (Spinner) findViewById(R.id.sort_options_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sort_options,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemSelectedListener(this);

        showListButton = (Button) findViewById(R.id.show_list_button);
        comicsArray = Comic.listAll(Comic.class);

        comicsArrayList = new ArrayList<>();

        comicsArrayList.addAll(comicsArray);

        //Collections.sort(comicsArrayList,Comic.TitleAndVolumeComparator);
        ArrayList sortedComics = (ArrayList) Ordering.from(Comic.TitleComparator).compound(Comic.ByVolumeComparator).sortedCopy(comicsArray);
        comicsArrayList = sortedComics;



        showListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(getApplicationContext(),AddComic.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getItemAtPosition(position).toString()) {
            case "Title" :
                intent.removeExtra("sortType");
                intent.putExtra("sortType","title");
                break;
            case "Publisher" :
                intent.removeExtra("sortType");
                intent.putExtra("sortType","publisher");
                break;
            case "Writer" :
                intent.removeExtra("sortType");
                intent.putExtra("sortType","writer");
                break;
            case "Artist" :
                intent.removeExtra("sortType");
                intent.putExtra("sortType","artist");
                break;
            case "Date" :
                intent.removeExtra("sortType");
                intent.putExtra("sortType","date");
                break;
            default:
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
