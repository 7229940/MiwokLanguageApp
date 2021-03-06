package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gaurav vashishtha on 03-04-2018.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color for this list of words */
    private int mColorResourceId;

   // * @param colorResourceId is the resource ID for the background color for this list of words
    public WordAdapter(Activity context, ArrayList<Word> words,int colorResourceId) {
        super(context,0,words);
        mColorResourceId=colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;

        if(listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.list_item, parent, false);

        }



        // Get the {@link AndroidFlavor} object located at this position in the list

        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        // Get the version name from the current AndroidFlavor object and

        // set this text on the name TextView

        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);

        // Get the version number from the current AndroidFlavor object and

        // set this text on the number TextView

        defaultTextView.setText(currentWord.getDefaultTranslation());
//


        // Find the ImageView in the list_item.xml layout with the ID list_item_icon

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.myImgView);

        // Get the image resource ID from the current AndroidFlavor object and

        // set the image to iconView

        if(currentWord.hasImage()){
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }
       //set the theme color for the list item
        View textContainer=listItemView.findViewById(R.id.text_container);
        //Find the color that the resourceId maps to
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        //set the background color of the text container view
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)

        // so that it can be shown in the ListView

        return listItemView;
    }
}
