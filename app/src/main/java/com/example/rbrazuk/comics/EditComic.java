package com.example.rbrazuk.comics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditComic extends AppCompatActivity {

    EditText etTitle;
    EditText etVolume;
    EditText etPublisher;
    EditText etWriter;
    EditText etArtist;
    Button btSave;
    Comic comic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comic);

        etTitle = (EditText) findViewById(R.id.et_title);
        etVolume = (EditText) findViewById(R.id.et_volume);
        etPublisher = (EditText) findViewById(R.id.et_publisher);
        etWriter = (EditText) findViewById(R.id.et_writer);
        etArtist = (EditText) findViewById(R.id.et_artist);
        btSave = (Button) findViewById(R.id.bt_save);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comic.setTitle(etTitle.getText().toString());
                comic.setPublisher(etPublisher.getText().toString());
                comic.setVolume(etVolume.getText().toString());
                comic.setArtist(etArtist.getText().toString());
                comic.setWriter(etWriter.getText().toString());
                comic.save();

                Intent intent = new Intent(EditComic.this,ComicDetail.class);
                intent.putExtra("id",comic.getId());
                startActivity(intent);
            }
        });

        Long id = getIntent().getLongExtra("id", -1L);

        comic = Comic.findById(Comic.class,id);

        etTitle.setText(comic.getTitle());
        etVolume.setText(comic.getVolume());
        etPublisher.setText(comic.getPublisher());
        etWriter.setText(comic.getWriter());
        etArtist.setText(comic.getArtist());

    }
}
