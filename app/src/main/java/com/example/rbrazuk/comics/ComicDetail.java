package com.example.rbrazuk.comics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ComicDetail extends AppCompatActivity {

    TextView mTitle;
    TextView mPublisher;
    TextView mVolume;
    TextView mWriter;
    TextView mArtist;
    TextView mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle = (TextView) findViewById(R.id.comic_detail_title);
        mVolume = (TextView) findViewById(R.id.comic_detail_volume);
        mPublisher = (TextView) findViewById(R.id.comic_detail_publisher);
        mWriter = (TextView) findViewById(R.id.comic_detail_writer);
        mArtist = (TextView) findViewById(R.id.comic_detail_artist);
        mDate = (TextView) findViewById(R.id.comic_detail_date);

        Long idNumber = getIntent().getLongExtra("id",-1L);

        Comic comic = Comic.findById(Comic.class,idNumber);
        System.out.println(comic.getTitle());

        mTitle.setText(comic.getTitle());
        mVolume.setText(comic.getVolume());
        mPublisher.setText(comic.getPublisher());
        mWriter.setText(comic.getWriter());
        mArtist.setText(comic.getArtist());
        mDate.setText(comic.getLocalDate());

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
}
