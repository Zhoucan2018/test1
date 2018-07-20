package com.example.zc.test1;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


public class MainActivity extends Activity {

    private ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_activity_main);
        Bmob.initialize(this, "140abfa6ef52e9f0e0f8fdfcbdb9b06e");

        add=(ImageView)findViewById(R.id.add);
        registerForContextMenu( add );
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("上传");
        getMenuInflater().inflate(R.menu.manage, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
//            case R.id.delete:
//                delete(item); // 代码见后
//                return true;
            case R.id.update :
                update(item); // 代码见后
                return true;
            default:
                return false;
        }
    }

    public void update(MenuItem item){

        final AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Intent intent =new Intent(MainActivity.this, Zcupdate.class);
        startActivity(intent);
    }

    //下载并跳转播放界面
    private void download(BmobFile file) {

        file.download(new DownloadFileListener() {
            @Override
            public void done(String path, BmobException e) {
                if(e==null)
                {
//                    Toast.makeText(MainActivity.this,path,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Zcplayvideo.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("path", path);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }

            @Override
            public void onProgress(Integer integer, long l) {

            }
        });


    }

    public void playvideo1(View view) {

        BmobQuery<Video>query=new BmobQuery<>();
        query.getObject("cdeeec2b39", new QueryListener<Video>() {
            @Override
            public void done(Video video, BmobException e) {

                if(e==null)
                {
                    download(video.getFile());
                }
            }
        });

    }


    public void playvideo2(View view) {

        BmobQuery<Video>query=new BmobQuery<>();
        query.getObject("65f384a521", new QueryListener<Video>() {
            @Override
            public void done(Video video, BmobException e) {

                if(e==null)
                {
                    download(video.getFile());
                }
            }
        });
    }



    public void playvideo3(View view) {

        BmobQuery<Video>query=new BmobQuery<>();
        query.getObject("0942751103", new QueryListener<Video>() {
            @Override
            public void done(Video video, BmobException e) {

                if(e==null)
                {
                    download(video.getFile());
                }
            }
        });
    }

    public void playvideo4(View view) {

        BmobQuery<Video>query=new BmobQuery<>();
        query.getObject("9e4959aa79", new QueryListener<Video>() {
            @Override
            public void done(Video video, BmobException e) {

                if(e==null)
                {
                    download(video.getFile());
                }
            }
        });


    }

    public void searchvideo(View view) {

        final EditText et = new EditText(this);
        new AlertDialog.Builder(MainActivity.this,R.style.Theme_AppCompat_Light_Dialog_Alert).setTitle("搜索视频")
                .setIcon(android.R.drawable.ic_menu_search)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        Toast.makeText(getApplicationContext(), et.getText().toString(),Toast.LENGTH_LONG).show();
//                        Intent intent=new Intent(MainActivity.this,Zcsearchvideo.class);
//                        Bundle bundle=new Bundle();
//                        bundle.putString("name", et.getText().toString());
//                        intent.putExtras(bundle);
//                        startActivity(intent);


                    }
                }).setNegativeButton("取消",null).show();
    }


//    public void createuser(View view) {
//
//        Video video=new Video();
//        video.setVideoname("鸡蛋饼");
//        video.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//
//    public void savevideo(View view) {
//        String path = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/Waffle.mp4";
//        final BmobFile file=new BmobFile(new File(path));
//        file.upload(new UploadFileListener() {
//            @Override
//            public void done(BmobException e) {
//                if(e==null)
//                {
//                    saveFile(file);
//                }
//            }
//        });
//
//
//    }
//
//    private void saveFile(BmobFile file) {
//
//        Video video=new Video("9e4959aa79");
//        video.setFile(file);
//        video.update(new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//
//                if(e==null)
//                {
//                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
//
//
//                }
//            }
//        });
//    }


//    public void Savevideo(View view) {
//        String path = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/noodles.mp4";
//        final BmobFile file=new BmobFile(new File(path));
//        file.upload(new UploadFileListener() {
//            @Override
//            public void done(BmobException e) {
//                if(e==null)
//                {
//                    saveFile(file);
//                }
//            }
//        });
//
//    }
//
//    private void saveFile(BmobFile file) {
//
//        Video video=new Video("cdeeec2b39");
//        video.setFile(file);
//        video.update(new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//
//                if(e==null)
//                {
//                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
//
//
//                }
//            }
//        });
//    }
//
//
//    public void creatname(View view) {
//
//        Video video=new Video();
//        video.setVideoname("拌面");
//        video.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//
//
//
//    private void download(BmobFile file) {
//
//        file.download(new DownloadFileListener() {
//            @Override
//            public void done(String path, BmobException e) {
//                if(e==null)
//                {
////                    Toast.makeText(MainActivity.this,path,Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(MainActivity.this, Zcplayvideo.class);
//                    Bundle bundle = new Bundle();
//
//                    bundle.putString("path", path);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//
//                }
//            }
//
//            @Override
//            public void onProgress(Integer integer, long l) {
//
//            }
//        });
//
//
//
//    }
//
//    public void playvideo(View view) {
//
//
//        BmobQuery<Video>query=new BmobQuery<>();
//        query.getObject("cdeeec2b39", new QueryListener<Video>() {
//            @Override
//            public void done(Video video, BmobException e) {
//
//                if(e==null)
//                {
//                    download(video.getFile());
//                }
//            }
//        });
//    }
}
