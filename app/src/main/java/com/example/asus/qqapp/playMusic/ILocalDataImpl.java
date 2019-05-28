package com.example.asus.qqapp.playMusic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.asus.qqapp.bean.MusicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/1/19.
 */

public class ILocalDataImpl implements ILocalData{
    @Override
    public List<MusicBean> getLocalMusic(Context context) {
        List<MusicBean> list = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String musicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            MusicBean bean = new MusicBean();
            bean.setSongname(musicName);
            bean.setUrl(musicPath);
            bean.setSingername(artist);
            bean.setSeconds((int) (duration/1000));
            list.add(bean);
        }
        cursor.close();
        return list;
    }
}
