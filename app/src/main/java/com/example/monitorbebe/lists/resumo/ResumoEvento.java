package com.example.monitorbebe.lists.resumo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.monitorbebe.database.Evento;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResumoEvento implements Serializable {

    private Date data;
    private List<Evento> eventos;
    private int dormiuInfo;
    private int mamouInfo;
    private int trocouInfo;

    public ResumoEvento(Date data) {
        this.data = data;
        this.dormiuInfo = 0;
        this.mamouInfo = 0;
        this.trocouInfo = 0;
        this.eventos = new ArrayList<>();
    }

    public Date getData() {
        return data;
    }

    public String getDormiuInfo() {
        return "Bebe dormiu por " + dormiuInfo + " horas";
    }

    public String getMamouInfo() { return "Bebe mamou " + mamouInfo + " vezes"; }

    public String getTrocouInfo() {
        return "Bebe foi trocado " + trocouInfo + " vezes";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addEvento(Evento evento){
        eventos.add(evento);
        if (evento.getTipoEvento().equals("Trocou")){
            this.trocouInfo += 1;
        }else if (evento.getTipoEvento().equals("Mamou")){
            this.mamouInfo += 1;
        }else{
            calcularDormiu();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calcularDormiu(){
        try {
        long dormiu = 0;
        for (Evento e : eventos){
            if(e.getTipoEvento().equals("Acordou")){
                if(eventos.indexOf(e) == 0 ){
                    long meiaNoite = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(e.getData())+"-00:00").getTime();
                    long acordouEvento =  e.getData().getTime();
                    long timeDiff = acordouEvento - meiaNoite;
                    dormiu += timeDiff / 3600000;
                }
                else{
                    long ultimoEvento = eventos.get(eventos.indexOf(e)-1).getData().getTime();
                    long acordouEvento =  e.getData().getTime();
                    long timeDiff = acordouEvento - ultimoEvento;
                    dormiu += timeDiff / 3600000;
                }
            }
            else if(e.getTipoEvento().equals("Dormiu") && eventos.indexOf(e) == (eventos.size()-1)){
                long meiaNoite = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(new SimpleDateFormat("dd/MM/yyyy").format(e.getData())+"-23:59").getTime();
                long dormiuEvento =  e.getData().getTime();
                long timeDiff = meiaNoite - dormiuEvento;
                dormiu += timeDiff / 3600000;
            }
        }
        dormiuInfo = Math.toIntExact(dormiu);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public List<Evento> getEvento(){
        return eventos;
    }



}


