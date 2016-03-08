package com.example.rbrazuk.comics;

import com.orm.SugarRecord;

import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
    private String mYearReleased;


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

    public static Comparator<Comic> PublisherComparator = new Comparator<Comic>() {
        @Override
        public int compare(Comic c1, Comic c2) {

            String publisher1 = c1.getPublisher().toUpperCase();
            String publisher2 = c2.getPublisher().toUpperCase();
            return publisher1.compareTo(publisher2);
        }
    };

    public static Comparator<Comic> TitleAndVolumeComparator = new Comparator<Comic>() {
        @Override
        public int compare(Comic c1, Comic c2) {
            int c;
            c = c1.getTitle().compareTo(c2.getTitle());
            if(c==0) {
                c = c1.getVolume().compareTo(c2.getVolume());
            }
            return c;
        }
    };

    public static Comparator<Comic> ByVolumeComparator = new Comparator<Comic>() {
        @Override
        public int compare(Comic c1, Comic c2) {
            Integer vol1 = Integer.valueOf(c1.getVolume());
            Integer vol2 = Integer.valueOf(c2.getVolume());
            return vol1.compareTo(vol2);
        }
    };




    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
