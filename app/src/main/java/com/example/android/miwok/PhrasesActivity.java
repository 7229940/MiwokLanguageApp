package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
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
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));
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

        WordAdapter  adapter=new WordAdapter(this,words,R.color.category_phrases);

        // ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this,R.layout.list_item, word);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=words.get(position);
                Toast.makeText(getApplicationContext(),"sound is going to be produced",Toast.LENGTH_SHORT).show();
                mediaPlayer= MediaPlayer.create(getApplicationContext(),word.getAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}