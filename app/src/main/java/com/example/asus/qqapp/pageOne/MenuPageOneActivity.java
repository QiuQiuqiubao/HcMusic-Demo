package com.example.asus.qqapp.pageOne;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.qqapp.R;
import com.example.asus.qqapp.Util.PlayUtil;
import com.example.asus.qqapp.playMusic.PlayListActivity;
import com.example.asus.qqapp.playMusic.PlayMusicActivity;
import com.youth.banner.Banner;

public class MenuPageOneActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActionBar ab;
    private Toolbar toolbar;
    private TextView tv_loginName;
    private long mkeyTime;//时间长度声明
    private Toast mtoast;//Toast提示声明
    private Banner banner;
    private ImageView login_imageView,playOrPause;
    private TextView l_bendi,l_quku;

    //设置图片资源:url或本地资源
    String[] images = new String[]{
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/236564.jpg",
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/236704.jpg",
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/234923.jpg",
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/236619.jpg",
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/236753.jpg",
            "https://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/235397.jpg"
    };

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page_one);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolBar();
        initViews();//初始化控件
        showResult();
        bannerTx();
        onClick();

        //控制侧滑菜单
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //设定NavigationView菜单的选择事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //点击事件
    private void onClick() {
        l_quku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = ProgressDialog.show(MenuPageOneActivity.this, null, "代码完善中……");
                progress.setCancelable(true);
            }
        });

        l_bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuPageOneActivity.this, PlayListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        tv_loginName = (TextView) findViewById(R.id.tv_loginName);
        login_imageView = (ImageView) findViewById(R.id.login_imageview);
        l_bendi= (TextView ) findViewById(R.id.l_bendi);
        l_quku= (TextView ) findViewById(R.id.l_quku);
        playOrPause= (ImageView) findViewById(R.id.play_or_pause);
    }

    private void setToolBar() {
        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.maintabbar_button_setting_normal);
        ab.setWindowTitle("我的");
        ab.setTitle("");
    }


    //后退键
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Toast.makeText(MenuPageOneActivity.this, "成功点击", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
                finish();
        }
        //关闭侧滑菜单
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void bannerTx() {
        banner = (Banner) findViewById(R.id.banner);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        banner.setIndicatorGravity(Banner.CENTER);
        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(3000);
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getApplicationContext()).load(url).into(view);
            }
        });

        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showResult() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("TOURIST") == null && bundle.getString("Vip") != null) {
            //传会员登录
            tv_loginName.setText(bundle.getString("Vip") + " ");
            login_imageView.setImageResource(R.drawable.huiyuanlogin);
        }
        if (bundle.getString("Vip") == null && bundle.getString("TOURIST") != null) {
            // 传游客登录
            tv_loginName.setText(bundle.getString("TOURIST") + " ");
            login_imageView.setImageResource(R.drawable.youkelogin);
        }
    }

    //退出程序Toast提示
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                if (mtoast != null) {
                    mtoast.setText("在按一次回到桌面");
                } else {
                    mtoast = Toast.makeText(MenuPageOneActivity.this, "再按一次回到桌面", Toast.LENGTH_SHORT);
                }
                mtoast.show();//显示Toast信息
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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


    //跳转播放音乐界面
    public void goPlayerActivity(View view) {
        Intent intent = new Intent(this, PlayMusicActivity.class);
        startActivity(intent);
    }
}