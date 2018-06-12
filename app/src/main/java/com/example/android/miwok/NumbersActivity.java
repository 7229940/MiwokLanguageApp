package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
       //make the arrayList final so that we can access it in the setOnItemClickListener
        final ArrayList<Word> words = new ArrayList<Word>();
        //word.add("one");
//        word.add("two");
//        word.add("three");
//        word.add("four");
//        word.add("five");
//        word.add("six");
//        word.add("seven");
//        word.add("eight");
//        word.add("nine ");
//        word.add("ten");
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));


        //  LinearLayout rootView=(LinearLayout)findViewById(R.id.rootView);
       // java.util.ArrayList<TextView> textViews= new ArrayList<TextView>();
//        TextView wordView=new TextView(this);

//        wordView.setText(word.get(0));
//        rootView.addView(wordView);
//        for(int i=0;i<10;i++)
//        {
//            TextView wordView=new TextView(this);
//            wordView.setText(word.get(i));
//            rootView.addView(wordView);
//        }

        WordAdapter  adapter=new WordAdapter(this,words,R.color.category_numbers);

       // ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this,R.layout.list_item, word);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                Toast.makeText(getApplicationContext(),"sound is going to be produced",Toast.LENGTH_SHORT).show();
                mediaPlayer=MediaPlayer.create(getApplicationContext(),word.getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}