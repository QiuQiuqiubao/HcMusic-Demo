package com.example.asus.qqapp.playMusic.paresenter;

import android.content.Context;

import com.example.asus.qqapp.playMusic.ILocalData;
import com.example.asus.qqapp.playMusic.ILocalDataImpl;
import com.example.asus.qqapp.playMusic.ILocalMusicView;

/**
 * Created by asus on 2018/1/19.
 */

public class LocalMusicPresenter implements BasePresenter{

    private ILocalMusicView iLocalMusicView;
    private ILocalData iLocalData;

    public LocalMusicPresenter(ILocalMusicView iLocalMusicView) {
        this.iLocalMusicView = iLocalMusicView;
        iLocalData = new ILocalDataImpl();
    }

    public void start(Context context) {
        iLocalMusicView.initLv(iLocalData.getLocalMusic(context));
    }

    @Override
    public void start() {
    }
}
