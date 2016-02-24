package com.example.rbrazuk.comics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Calendar;

public class AddComic extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button mSaveButton;
    EditText mArtist;
    EditText mTitle;
    EditText mWriter;
    EditText mPublisher;
    EditText mVolume;
    Button mDateButton;
    Comic comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comic);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSaveButton = (Button) findViewById(R.id.button_save_comic);
        mTitle = (EditText) findViewById(R.id.edit_text_title);
        mPublisher = (EditText) findViewById(R.id.edit_text_publisher);
        mWriter = (EditText) findViewById(R.id.edit_text_writer);
        mArtist = (EditText) findViewById(R.id.edit_text_artist);
        mVolume = (EditText) findViewById(R.id.edit_text_volume);
        mDateButton = (Button) findViewById(R.id.button_set_date);

        comic = new Comic();

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = mTitle.getText().toString();

                if(TextUtils.isEmpty(title)) {
                    mTitle.setError("Comic must have title");
                    return;
                } else {
                    comic.setTitle(mTitle.getText().toString());
                    comic.setVolume(mVolume.getText().toString());
                    comic.setWriter(mWriter.getText().toString());
                    comic.setArtist(mArtist.getText().toString());
                    comic.setPublisher(mPublisher.getText().toString());
                    System.out.println("EDIT TEXT SAYS " + mTitle.getText().toString());
                    comic.save();

                    Toast.makeText(getApplicationContext(),"Comic Saved",Toast.LENGTH_LONG).show();
                }
            }
        });

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getSupportFragmentManager(),"datePicker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view,int year, int monthOfYear, int dayOfMonth) {

        LocalDate localDate = new LocalDate(view.getYear(),view.getMonth(),view.getDayOfMonth());

        comic.setLocalDate(localDate.toString());
        mDateButton.setText(localDate.toString());

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
