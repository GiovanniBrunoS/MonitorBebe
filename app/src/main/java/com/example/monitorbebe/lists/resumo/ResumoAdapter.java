package com.example.monitorbebe.lists.resumo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorbebe.MainActivity;
import com.example.monitorbebe.NovoEventoActivity;
import com.example.monitorbebe.R;
import com.example.monitorbebe.database.Evento;
import com.example.monitorbebe.database.EventoViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResumoAdapter extends RecyclerView.Adapter implements Serializable {

    private List<Evento> mEventos;
    private List<ResumoEvento> rEventos;

    public ResumoAdapter() {
        this.mEventos = new ArrayList<>();
        this.rEventos = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumo_card_item, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        EventoViewHolder viewHolder = (EventoViewHolder)holder;
        viewHolder.dataInfo.setText(new SimpleDateFormat("dd/MM/yyyy").format(rEventos.get(position).getData()));
        viewHolder.horaInfo.setText(new SimpleDateFormat("HH:mm").format(rEventos.get(position).getData()));
        viewHolder.dormiuInfo.setText(rEventos.get(position).getDormiuInfo());
        viewHolder.trocouInfo.setText(rEventos.get(position).getTrocouInfo());
        viewHolder.mamouInfo.setText(rEventos.get(position).getMamouInfo());
    }

    @Override
    public int getItemCount() {
        fazerResumo();
        if (rEventos != null)
            return rEventos.size();
        else return 0;
    }

    public void setEventos(List<Evento> eventos){
        this.mEventos = eventos;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fazerResumo(){
        for (Evento e : mEventos ) {
            Boolean resumoNaoExiste = true;
            for (ResumoEvento resumoEvento : rEventos){
                String resumoEventoDate = resumoEvento.getData().getDay() + "/" + resumoEvento.getData().getMonth() + "/" + resumoEvento.getData().getYear();
                String eventoDate = e.getData().getDay() + "/" + e.getData().getMonth() + "/" + e.getData().getYear();
                if (resumoEventoDate.equals(eventoDate)){
                    resumoNaoExiste = false;
                    Boolean idNaoExiste = true;
                    for(Evento eventoResumo : resumoEvento.getEvento() ){
                        if (eventoResumo.getId().equals(e.getId())){
                            idNaoExiste = false;
                        }
                    }
                    if (idNaoExiste)
                        resumoEvento.addEvento(e);
                }
            }
            if (resumoNaoExiste){
                ResumoEvento novoResumo = new ResumoEvento(e.getData());
                novoResumo.addEvento(e);
                rEventos.add(novoResumo);
            }
        }
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView dataInfo;
        TextView horaInfo;
        TextView dormiuInfo;
        TextView trocouInfo;
        TextView mamouInfo;

        private EventoViewHolder(View itemView) {
            super(itemView);
            dataInfo = itemView.findViewById(R.id.dataInfo);
            horaInfo = itemView.findViewById(R.id.horaInfo);
            dormiuInfo = itemView.findViewById(R.id.dormiuInfo);
            trocouInfo = itemView.findViewById(R.id.trocouInfo);
            mamouInfo = itemView.findViewById(R.id.mamouInfo);
        }
    }

}
