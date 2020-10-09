package com.example.monitorbebe.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "eventos_table")
public class Evento implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private Integer id;

    @ColumnInfo(name = "data")
    @NonNull
    private Date data;
    @ColumnInfo(name = "evento")
    @NonNull
    private String tipoEvento;

    public Evento(){
    }

    public Evento(Date data, String evento){
        this.data = data;
        this.tipoEvento = evento;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public Date getData() {
        return data;
    }

    public void setData(@NonNull Date data) {
        this.data = data;
    }

    @NonNull
    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(@NonNull String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
