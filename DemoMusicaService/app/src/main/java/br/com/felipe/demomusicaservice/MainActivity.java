package br.com.felipe.demomusicaservice;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.felipe.demomusicaservice.adapters.ListaAdapter;
import br.com.felipe.demomusicaservice.modal.Musica;
import br.com.felipe.demomusicaservice.service.MusicaService;
import br.com.felipe.demomusicaservice.service.MusicaServiceConnection;


public class MainActivity extends ActionBarActivity {

    private Button btTocar;
    private Button btPausar;
    private Button btParar;
    private Intent intent;
    private ListView lvMusicas;
    private TextView tvMusica;
    private List<Musica> musicas;

    private MusicaServiceConnection connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaServico();
        conectaServico();

        btTocar = (Button) findViewById(R.id.btTocar);
        btPausar = (Button) findViewById(R.id.btPausar);
        btParar = (Button) findViewById(R.id.btParar);
        lvMusicas  = (ListView) findViewById(R.id.lvMusicas);
        tvMusica = (TextView) findViewById(R.id.tvMusica);

        btTocar.setEnabled(false);

        musicas = new ArrayList();
        musicas.add(new Musica("Van Halen", "musica.mp3"));
        musicas.add(new Musica("Iron Maiden", "ironmaiden.mp3"));
        musicas.add(new Musica("Guns N' Roses", "guns.mp3"));

        ListaAdapter adapter = new ListaAdapter(this, musicas);
        lvMusicas.setAdapter(adapter);

        lvMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setMusica(position);
            }
        });
    }
    // metodo responsavel por iniciar o serviço
    public void iniciaServico(){
        intent = new Intent(this, MusicaService.class);
        startService(intent);
    }

    private void conectaServico(){
        connection = new MusicaServiceConnection();
        bindService(intent, connection, 0);
    }
    public void setMusica(int position) {
        connection.getMusicaService().setMusica(musicas.get(position).getArquivo());
        tvMusica.setText(musicas.get(position).getNome());
        ativarDesativarBotoes(true);
    }
    public void tocar(View v){
        connection.getMusicaService().tocar();
        ativarDesativarBotoes(true);
    }

    public void pausar(View v){
        connection.getMusicaService().pausar();
        ativarDesativarBotoes(false);
    }

    public void parar(View v){
        connection.getMusicaService().parar();
        tvMusica.setText(R.string.tvmusica);
        btTocar.setEnabled(false);
        btPausar.setEnabled(false);
        btParar.setEnabled(false);
    }

    private void ativarDesativarBotoes(boolean estaTocando) {
        btTocar.setEnabled(!estaTocando);
        btPausar.setEnabled(estaTocando);
        btParar.setEnabled(estaTocando);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(connection);
    }
}
