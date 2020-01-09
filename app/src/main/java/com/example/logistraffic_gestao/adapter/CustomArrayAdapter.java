package com.example.logistraffic_gestao.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.logistraffic_gestao.Carga;
import com.example.logistraffic_gestao.R;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<Carga> {
    public CustomArrayAdapter(Context context, ArrayList<Carga> users){
        super(context,0,users);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){
        Carga c = getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.layout_linha,parent,false);
        }

        ((TextView) convertView.findViewById(R.id.nome_carga)).setText(c.getNome());
        ((TextView) convertView.findViewById(R.id.tipo_carga)).setText(c.getTipo());
        ((TextView) convertView.findViewById(R.id.lugar)).setText(c.getCais());
        ((TextView) convertView.findViewById(R.id.matricula)).setText(c.getMatricula());


        return convertView;
    }
}
