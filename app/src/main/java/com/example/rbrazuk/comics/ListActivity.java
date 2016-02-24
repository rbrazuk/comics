package com.example.rbrazuk.comics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView mListView;
    ComicsAdapter mComicsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateData();

        mListView = (ListView) findViewById(R.id.comics_list_view);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("Are you sure you want to delete this item?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Comic comic = (Comic) parent.getItemAtPosition(position);
                        comic.delete();
                        updateData();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Comic comic = (Comic) parent.getItemAtPosition(position);

                Intent i = new Intent(ListActivity.this,ComicDetail.class);
                i.putExtra("id",comic.getId());
                startActivity(i);

                Long idNumber = comic.getId();
                System.out.println(idNumber);


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
    public void onResume() {
        super.onResume();

        updateData();
    }

    private void updateData() {

        List<Comic> comicArray = Comic.listAll(Comic.class);
        ArrayList comicArrayList = new ArrayList();
        comicArrayList.addAll(comicArray);
        mComicsAdapter = new ComicsAdapter(this,comicArrayList);
        mListView = (ListView) findViewById(R.id.comics_list_view);


        mListView.setAdapter(mComicsAdapter);

        mComicsAdapter.notifyDataSetChanged();
        System.out.println("update data called");



    }

}
