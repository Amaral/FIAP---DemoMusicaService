package br.com.felipe.demomusicaservice.binder;

import android.os.Binder;
import android.os.IBinder;

import br.com.felipe.demomusicaservice.service.MusicaService;

/**
 * Created by rm49345 on 24/08/2015.
 */
public class MusicaBinder extends Binder {

    private MusicaService musicaService;

    public MusicaBinder(MusicaService musicaService){
        this.musicaService = musicaService;
    }
    // Metodo responsavel por permitir acesso ao service
    public MusicaService getMusicaService() {
        return musicaService;
    }


}
