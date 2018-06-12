package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

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

        WordAdapter  adapter=new WordAdapter(this,words,R.color.category_family);

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