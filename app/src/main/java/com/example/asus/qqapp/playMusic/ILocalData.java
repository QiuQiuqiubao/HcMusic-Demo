package com.example.asus.qqapp.playMusic;

import android.content.Context;

import com.example.asus.qqapp.bean.MusicBean;

import java.util.List;

/**
 * Created by asus on 2018/1/19.
 */

public interface ILocalData {
    List<MusicBean> getLocalMusic(Context context);
}
