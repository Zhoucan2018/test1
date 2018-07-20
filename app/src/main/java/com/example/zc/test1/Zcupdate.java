package com.example.zc.test1;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Zcupdate extends Activity {

    private EditText etvideoname;
    private EditText etvideopath;
    private EditText etvideoid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_update);
        Bmob.initialize(this, "140abfa6ef52e9f0e0f8fdfcbdb9b06e");

        etvideoname=(EditText)findViewById(R.id.etvideoname);
        etvideopath=(EditText)findViewById(R.id.etvideopath);
        etvideoid=(EditText)findViewById(R.id.etvideoid);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }
    }


    public void createvideoname(View view) {

        Video video=new Video();
       video.setVideoname(etvideoname.getText().toString());
        video.save(new SaveListener<String>() {
           @Override
           public void done(String s, BmobException e) {
               if(e==null){
                    Toast.makeText(Zcupdate.this,s,Toast.LENGTH_LONG).show();
               }else{
                    Toast.makeText(Zcupdate.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
          }
        });

    }

    public void updatevideo(View view) {

        String path= Environment.getExternalStorageDirectory().getPath()+etvideopath.getText().toString();

        final BmobFile file=new BmobFile(new File(path));
//        final Video video1=new Video();
//        final String videoid;
//        videoid=video1.getObjectId();
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null)
                {
//                    Toast.makeText(Zcupdate.this,"update success",Toast.LENGTH_LONG).show();
                      savevideo(file);
                }
            }
        });


    }

    private void savevideo(BmobFile file) {

        Video video=new Video(etvideoid.getText().toString());
       video.setFile(file);
        video.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {

                if(e==null)
                {
                    Toast.makeText(Zcupdate.this,"Success",Toast.LENGTH_LONG).show();


               }
           }
        });
    }
}
