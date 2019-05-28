package com.example.asus.qqapp.playMusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.qqapp.R;
import com.example.asus.qqapp.playMusic.adapter.LocalVpAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {

    private ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LocalMusicFragment());
        String[] titles = new String[]{};
        viewpager.setAdapter(new LocalVpAdapter(getSupportFragmentManager(), fragments, titles));
    }
}
