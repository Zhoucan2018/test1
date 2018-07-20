package com.example.zc.test1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class Zcplayvideo extends Activity {

    private VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_video);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }else
            initView();


    }
    private void initView(){
        video=(VideoView) findViewById(R.id.video);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String path=bundle.getString("path");
        File file=new File(path);
        if(!file.exists()){
            Toast.makeText(Zcplayvideo.this, "视频路径错误", Toast.LENGTH_SHORT).show();
            return;
        }
        video.setVideoPath(file.getAbsolutePath());
        video.setMediaController(new MediaController(Zcplayvideo.this));
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                video.start();
            }
        });



    }
    private void exit(){
        this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initView();
                }else {
                    Toast.makeText(this, "您拒绝了此权限，该软件无法使用", Toast.LENGTH_SHORT).show();
                    exit();
                }
        }
    }


}
