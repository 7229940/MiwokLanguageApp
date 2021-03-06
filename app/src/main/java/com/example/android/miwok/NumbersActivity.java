package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }  else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mMediaPlayer.start();
                    }
                }
            };
    // this is the oncomletionListener for an audio file
    // we have created it globally so that it would not be needed to make a new object for each click.

    private MediaPlayer.OnCompletionListener mOnCompletionListener= new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }

    };

    //Handles audio focus when playing a sound file.
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAudioManager=(AudioManager)getSystemService(getApplicationContext().AUDIO_SERVICE);

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
                //Toast.makeText(getApplicationContext(),"sound is going to be produced",Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request temporary focus because we want to play an audio file of one or two second long.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // we have audio focus now
                    mMediaPlayer = MediaPlayer.create(getApplicationContext(), word.getAudioResourceId());
                    mMediaPlayer.start();

                    /**
                     * setup a listener on the media player so that we can stop or release the
                     * media player once the sounds has finished playing.
                     *
                     * Clean up the media player by releasing its resources on completion the audio file.
                     */

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        //when the activity is stopped, release the media player resources
        //becuse we don't want to play any sound any more.
        releaseMediaPlayer();
    }
}