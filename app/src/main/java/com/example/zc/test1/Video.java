package com.example.zc.test1;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Video extends BmobObject {

    private String Videoname;
    private BmobFile file;

    public Video(String ObjectId){
        this.setObjectId(ObjectId);

    }
    public Video()
    {

    }



    public String getVideoname() {
        return Videoname;
    }

    public void setVideoname(String videoname) {
        Videoname = videoname;
    }

    public BmobFile getFile() {
        return file;
    }


    public void setFile(BmobFile file) {
        this.file = file;
    }
}
