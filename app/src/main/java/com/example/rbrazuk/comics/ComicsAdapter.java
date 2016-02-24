package com.example.rbrazuk.comics;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rossbrazuk1 on 2/23/16.
 */
public class ComicsAdapter extends ArrayAdapter<Comic> {
    public ComicsAdapter(Context context,ArrayList<Comic> comics) {
        super(context, 0, comics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comic comic = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comic,parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.comic_list_view_title);
        TextView tvVolume = (TextView) convertView.findViewById(R.id.comic_list_view_volume);
        TextView tvWriter = (TextView) convertView.findViewById(R.id.comic_list_view_writer);
        TextView tvArtist = (TextView) convertView.findViewById(R.id.comic_list_view_artist);

        tvTitle.setText(comic.getTitle());

        String volume = comic.getVolume();
        if(TextUtils.isEmpty(volume)) {
            tvVolume.setText("");
        } else {
            tvVolume.setText("Vol " + volume);
        }

        tvWriter.setText(comic.getWriter());
        tvArtist.setText(comic.getArtist());

        return convertView;
    }
}