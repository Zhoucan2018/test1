package com.example.zc.test1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;

public class Zcsearchvideo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_activity_main);
        Bmob.initialize(this, "140abfa6ef52e9f0e0f8fdfcbdb9b06e");


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }

        initView();
    }

    private void initView() {

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("name");
        BmobQuery<Video> query=new BmobQuery<>();
        


    }
}
