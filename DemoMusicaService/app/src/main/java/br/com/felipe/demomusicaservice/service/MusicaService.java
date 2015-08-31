package br.com.felipe.demomusicaservice.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import br.com.felipe.demomusicaservice.binder.MusicaBinder;

public class MusicaService extends Service {

    private MediaPlayer mediaPlayer;

    // posicao atual da musica em milisegundos
    private int posicao = 0;

    // a musica a ser tocada, localizada no diretorio "assets"
    private String musica;

    private IBinder binder = new MusicaBinder(this);

    public MusicaService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void tocar() {
        if(posicao > 0) {
            mediaPlayer.seekTo(posicao);
        }else {
            mediaPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(musica);
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mediaPlayer.prepare();
            } catch (Exception ex) {

            }
        }
        mediaPlayer.start();
    }

    public void setMusica(String musica) {
        this.musica = musica;
        parar();
        tocar();
    }

    public void parar(){
        if (mediaPlayer != null) {
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                posicao = 0;
            }
        }
    }

    public void pausar() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            posicao = mediaPlayer.getCurrentPosition();
        }
    }
}
