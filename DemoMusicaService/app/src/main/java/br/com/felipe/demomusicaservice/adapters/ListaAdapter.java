package br.com.felipe.demomusicaservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.felipe.demomusicaservice.modal.Musica;


public class ListaAdapter extends BaseAdapter {

    private Context context;
    private List<Musica> lista;

    public ListaAdapter(Context context, List<Musica> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView tvNome = (TextView) convertView.findViewById(android.R.id.text1);
        tvNome.setText(lista.get(position).getNome());

        return convertView;
    }
}
