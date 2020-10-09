package com.example.monitorbebe.lists.acordou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class AcordouAdapter extends RecyclerView.Adapter implements Serializable {

    private List<Evento> mEventos;
    private int posicaoRemovidoRecentemente;
    private Evento eventoRemovidoRecentemente;
    private Activity activity;

    public AcordouAdapter(Activity activity) {
        this.activity = activity;
        this.mEventos = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_card_item, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        EventoViewHolder viewHolder = (EventoViewHolder)holder;
        viewHolder.dataInfo.setText(new SimpleDateFormat("dd/MM/yyyy").format(mEventos.get(position).getData()));
        viewHolder.horaInfo.setText(new SimpleDateFormat("HH:mm").format(mEventos.get(position).getData()));
        viewHolder.descInfo.setText(mEventos.get(position).getTipoEvento());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity.getBaseContext(), NovoEventoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("evento", mEventos.get(holder.getAdapterPosition()));
            bundle.putInt("request_code", MainActivity.REQUEST_EDITAR_EVENTO);
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, MainActivity.REQUEST_EDITAR_EVENTO);
        });
    }

    @Override
    public int getItemCount() {
        if (mEventos != null)
            return mEventos.size();
        else return 0;
    }

    public void setEventos(List<Evento> eventos){
        this.mEventos = eventos;
        notifyDataSetChanged();
    }

    public void remover(int position){
        posicaoRemovidoRecentemente = position;
        eventoRemovidoRecentemente = mEventos.get(position);
        EventoViewModel.delete(eventoRemovidoRecentemente);
        mEventos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.relative_layout), "Item deletado",Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", v -> {
            EventoViewModel.insert(eventoRemovidoRecentemente);
            mEventos.add(posicaoRemovidoRecentemente, eventoRemovidoRecentemente);
            notifyItemInserted(posicaoRemovidoRecentemente);
        });
        snackbar.show();
    }

    public void mover(int fromPosition, int toPosition){
        if (fromPosition < toPosition)
            for (int i = fromPosition; i < toPosition; i++)
                Collections.swap(mEventos, i, i+1);
        else
            for (int i = fromPosition; i > toPosition; i--)
                Collections.swap(mEventos, i, i-1);
        notifyItemMoved(fromPosition,toPosition);
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView dataInfo;
        TextView horaInfo;
        TextView descInfo;

        private EventoViewHolder(View itemView) {
            super(itemView);
            dataInfo = itemView.findViewById(R.id.dataInfo);
            horaInfo = itemView.findViewById(R.id.horaInfo);
            descInfo = itemView.findViewById(R.id.descInfo);
        }
    }

}
