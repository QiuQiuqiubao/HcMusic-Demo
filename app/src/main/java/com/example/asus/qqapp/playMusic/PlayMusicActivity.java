package com.example.asus.qqapp.playMusic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.qqapp.R;
import com.example.asus.qqapp.Util.PlayUtil;

import static com.example.asus.qqapp.R.id.play_or_pause;

public class PlayMusicActivity extends AppCompatActivity {


    private ImageView iv1;//收回播放界面
    private ImageView playOrPause;//播放or暂停

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        initViews();//初始化控件
        onClick();//点击事件
    }

    private void onClick() {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void playOrPause(View view) {
        if (PlayUtil.CURRENT_STATE == PlayUtil.STOP) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PLAY);
        } else if (PlayUtil.CURRENT_STATE == PlayUtil.PAUSE) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PAUSE);
        } else {
            playOrPause.setImageResource(R.drawable.ring_btnplay);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PAUSE);
        }
    }

    public void preMusic(View view) {
  //      if (PlayUtil.CURRENT_POSITION > 0) {
  //          PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION - 1;
    //        PlayUtil.currentMusic = PlayUtil.CURRENT_PLAY_LIST.get(PlayUtil.CURRENT_POSITION);
      //      PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
     //   }
    }

    public void nextMusic(View view) {
      //  if (PlayUtil.CURRENT_POSITION < PlayUtil.CURRENT_PLAY_LIST.size() - 1) {
     //       PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION + 1;
    //        PlayUtil.currentMusic = PlayUtil.CURRENT_PLAY_LIST.get(PlayUtil.CURRENT_POSITION);
     //       PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
     //   }
    }


    private void initViews() {
        iv1 = (ImageView) findViewById(R.id.iv1);
        playOrPause = (ImageView) findViewById(play_or_pause);
    }

}
