package com.example.asus.qqapp.playMusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.qqapp.BaseFragment;
import com.example.asus.qqapp.R;
import com.example.asus.qqapp.Util.PlayUtil;
import com.example.asus.qqapp.bean.MusicBean;
import com.example.asus.qqapp.playMusic.adapter.LocalMusicLvAdapter;
import com.example.asus.qqapp.playMusic.paresenter.LocalMusicPresenter;

import java.util.List;

/**
 * Created by asus on 2018/1/18.
 */

public class LocalMusicFragment extends BaseFragment implements ILocalMusicView{
    private LocalMusicPresenter localMusicPresenter = new LocalMusicPresenter(this);
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_local_list, container, false);
        initView(view);
        localMusicPresenter.start(getActivity());
        return view;
    }

    private void initView(View view) {
        lv = ((ListView) view.findViewById(R.id.lv));
    }

    @Override
    public void initLv(final List<MusicBean> list) {
        lv.setAdapter(new LocalMusicLvAdapter(list, getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayUtil.startService(getActivity(),list.get(position),PlayUtil.PLAY);
            }
        });
    }
    }
