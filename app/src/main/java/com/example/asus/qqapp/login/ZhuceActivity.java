package com.example.asus.qqapp.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.qqapp.LoginUserNameHelper;
import com.example.asus.qqapp.R;

public class ZhuceActivity extends AppCompatActivity {

    private Button OK;
    private Button Back;
    private EditText zhuce_name;
    private EditText zhuce_pass;
    private EditText zhuce_phone;
    private EditText zhuce_address;
    private String Name;
    private String Pass;
    private String Phone;
    private String Address;

    private SQLiteDatabase loginUserNameDb;
    private static final String DB_FILE = "admin.db";//库名
    private static final String DB_TABLE = "loginUserName";//表名


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        zhuce_name = (EditText) findViewById(R.id.name);
        zhuce_pass = (EditText) findViewById(R.id.password);
        zhuce_phone = (EditText) findViewById(R.id.phone);
        zhuce_address = (EditText) findViewById(R.id.address);
        OK = (Button) findViewById(R.id.OK);
        Back= (Button) findViewById(R.id.back);


        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = zhuce_name.getText().toString();
                Pass = zhuce_pass.getText().toString();
                Phone = zhuce_phone.getText().toString();
                Address = zhuce_address.getText().toString();
                Intent intent = new Intent(ZhuceActivity.this, LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("H_C", Name);
                intent.putExtras(bundle);
                startActivity(intent);

                //创建数据库
                LoginUserNameHelper loginUserNameHelper = new LoginUserNameHelper(getApplication(), DB_FILE, null, 1);
                loginUserNameDb = loginUserNameHelper.getWritableDatabase();
                //创建数据表
                createTable();
                //添加数据
                insert(Name, Pass, Phone, Address);
                Toast.makeText(ZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhuceActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void createTable() {
        Cursor cursor =loginUserNameDb.rawQuery("select distinct tbl_name from sqlite_master where tbl_name='" + DB_TABLE + "'", null);
        if (cursor != null) {
            if (cursor.getCount() == 0) {//没有表,建新表
                loginUserNameDb.execSQL("create table " + DB_TABLE + "(_id integer primary key,userName text not null,pass text not null,phone text,address text);");
                cursor.close();
            }
        }
    }

    public void insert(String name, String pass, String phone, String address) {
        ContentValues cv = new ContentValues();
        cv.put("userName", name);
        cv.put("pass", pass);
        cv.put("phone", phone);
        cv.put("address", address);
        loginUserNameDb.insert(DB_TABLE, null, cv);
        System.out.println(cv);
    }

}
