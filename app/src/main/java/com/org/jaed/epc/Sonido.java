package com.org.jaed.epc;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Jerry on 17/08/2015.
 */
public class Sonido {
    private MediaPlayer mp;
    private int Resid;
    private Context mContext;
    public Sonido(Context context) {
        this.mContext = context;
    }
    public void play(){
        mp = MediaPlayer.create(mContext, Resid);
        mp.start();
    }
    public void stop(){
        mp.stop();
    }
    public void setResid(int resid){
        this.Resid = resid;
        if(mp != null){
            mp.reset();
        }
    }
}
