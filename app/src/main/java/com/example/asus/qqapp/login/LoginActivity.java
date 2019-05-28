package com.example.asus.qqapp.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.qqapp.LoginUserNameHelper;
import com.example.asus.qqapp.R;
import com.example.asus.qqapp.pageOne.MenuPageOneActivity;

public class LoginActivity extends AppCompatActivity {


    private Button btn_sign;
    private TextView tv_zhuce;
    private EditText name;
    private EditText password;
    private TextView tx_PlayerLogin;

    private LoginUserNameHelper loginUserNameHelper;
    private SQLiteDatabase loginUserNameDb;
    private static final String DB_FILE = "admin.db";//库名
    private static final String DB_TABLE = "loginUserName";//表名



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginUserNameHelper = new LoginUserNameHelper(this, DB_FILE, null, 1);
        initViews();//初始化控件
        onClick();//点击事件
        showResult();//注册账号传参
    }

    private void showResult() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(LoginActivity.this, "欢迎使用豪驰音乐播放器", Toast.LENGTH_SHORT).show();
        } else {
            name.setText(bundle.getString("H_C") + "");
        }
    }


    private void initViews() {
        btn_sign = (Button) findViewById(R.id.btn_sign);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        tx_PlayerLogin = (TextView) findViewById(R.id.tx_playerLogin);
    }


    private void onClick() {
        //点击注册跳转页面
        tv_zhuce.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent(LoginActivity.this, ZhuceActivity.class);
                                             startActivity(intent);
                                             finish();
                                         }
                                     }
        );

        btn_sign.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!name.getText().toString().equals("") && !password.getText().toString().equals("")) {
                                                if (login(name.getText().toString(), password.getText().toString())) {
                                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                                    //跳转音乐界面首页 登录信息传参
                                                    Intent intent = new Intent(LoginActivity.this,MenuPageOneActivity.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putString("Vip", name.getText().toString());
                                                    intent.putExtras(bundle);
                                                    finish();
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                                                    password.setText("");
                                                }
                                            } else if (name.getText().toString().equals("") && password.getText().toString().equals("")) {
                                                Toast.makeText(LoginActivity.this, "提示：账号或者密码不能为空", Toast.LENGTH_SHORT).show();
                                            } else if (name.getText().toString().equals("")) {
                                                Toast.makeText(LoginActivity.this, "提示：请输入账号", Toast.LENGTH_SHORT).show();
                                            } else if (password.getText().toString().equals("")) {
                                                Toast.makeText(LoginActivity.this, "提示：请输入密码", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
        );

        tx_PlayerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MenuPageOneActivity.class);
                //传参游客信息
                Bundle bundle = new Bundle();
                bundle.putString("TOURIST", "游客");
                intent.putExtras(bundle);
                finish();
                startActivity(intent);
            }
        });

    }

    //登录方法
    public boolean login(String Name, String Password) {
        loginUserNameDb = loginUserNameHelper.getWritableDatabase();
        String sql = "select * from " + DB_TABLE + " where userName=? and pass=?";
        Cursor cursor = loginUserNameDb.rawQuery(sql, new String[]{Name, Password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }


}
