/**
 * Created by Gaurav vashishtha on 01-04-2018.
 */
package com.example.android.miwok;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**

 * {@link Word} represents a vocabulary word that the user wants to learn.

 * It contains a default translation and a Miwok translation for that word.

 */

public class Word {
    /** Default translation for the word */

    private String mDefaultTranslation;

    /** Miwok translation for the word */

    private String mMiwokTranslation;

    private int  mImageResourceId=NO_RESOURCE_ID;
    private static final int NO_RESOURCE_ID=-1;
    private int mAudioResourceId=NO_RESOURCE_ID;
    /**

     * Create a new Word object.

     *

     * @param defaultTranslation is the word in a language that the user is already familiar with

     *                           (such as English)

     * @param miwokTranslation is the word in the Miwok language

     */

    public Word(String defaultTranslation, String miwokTranslation,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId=audioResourceId;
    }
    /**
     +     * Create a new Word object.
     +     *
     +     * @param defaultTranslation is the word in a language that the user is already familiar with
     +     *                           (such as English)
     +     * @param miwokTranslation is the word in the Miwok language
     +     * @param imageResourceId is the drawable resource ID for the image associated with the word
     +     * @param songResourceId is the raw resource ID for the audio associated with the word.
     +     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int songResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId=imageResourceId;
        mAudioResourceId=songResourceId;
    }


    /**

     * Get the default translation of the word.

     */

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    /**

     * Get the Miwok translation of the word.

     */

    public String getMiwokTranslation() {

        return mMiwokTranslation;

    }

    public int getImageResourceId(){
        return mImageResourceId;
    }
    /*
    this hasImage method will return wether the current word is having any image or not

     */
    public boolean hasImage(){
        return (mImageResourceId != NO_RESOURCE_ID);
    }

    public int getAudioResourceId(){  return mAudioResourceId;  }
    public boolean hasSong(){   return (mAudioResourceId!=NO_RESOURCE_ID);   }

}