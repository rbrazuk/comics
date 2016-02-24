package com.example.rbrazuk.comics;

import com.orm.SugarRecord;

import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Comparator;


import java.util.Date;

/**
 * Created by rossbrazuk1 on 2/23/16.
 */
public class Comic extends SugarRecord implements Comparable {

    private String mTitle;
    private String mVolume;
    private String mPublisher;
    private String mWriter;
    private String mArtist;
    private Boolean mOnWishList;
    private String mLocalDate;


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getVolume() {
        return mVolume;
    }

    public void setVolume(String volume) {
        mVolume = volume;
    }

    public Boolean getOnWishList() {
        return mOnWishList;
    }

    public void setOnWishList(Boolean onWishList) {
        mOnWishList = onWishList;
    }

    public String getLocalDate() {
        return mLocalDate;
    }

    public void setLocalDate(String localDate) {
        mLocalDate = localDate;
    }

    public Comic(){

    }

    public static Comparator<Comic> TitleComparator = new Comparator<Comic>() {
        @Override
        public int compare(Comic c1, Comic c2) {

            String title1 = c1.getTitle().toUpperCase();
            String title2 = c2.getTitle().toUpperCase();

            return title1.compareTo(title2);


        }
    };


    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
