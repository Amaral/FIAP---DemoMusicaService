package br.com.felipe.demomusicaservice.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import br.com.felipe.demomusicaservice.binder.MusicaBinder;

/**
 * Created by rm49345 on 24/08/2015.
 */
public class MusicaServiceConnection implements ServiceConnection {

    private MusicaService musicaService;

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        MusicaBinder musicaBinder = (MusicaBinder) binder;
        this.musicaService = musicaBinder.getMusicaService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public MusicaService getMusicaService() {
        return musicaService;
    }
}
