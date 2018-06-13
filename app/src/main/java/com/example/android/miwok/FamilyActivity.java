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

public class FamilyActivity extends AppCompatActivity {

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