package com.example.monitorbebe.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventoDao {

    @Insert
    void insert(Evento evento);

    @Update
    void update(Evento evento);

    @Delete
    void delete(Evento evento);

    @Query("DELETE FROM eventos_table")
    void deleteAll();

    @Query("SELECT * from eventos_table ORDER BY data ASC")
    LiveData<List<Evento>> getAll();

    @Query("SELECT * from eventos_table WHERE data BETWEEN :daystart AND :dayend ORDER BY data ASC")
    LiveData<List<Evento>> getToday(Long daystart, Long dayend);

    @Query("SELECT * from eventos_table WHERE evento = 'Dormiu' ORDER BY data ASC")
    LiveData<List<Evento>> getAllDormiu();

    @Query("SELECT * from eventos_table WHERE evento = 'Acordou' ORDER BY data ASC")
    LiveData<List<Evento>> getAllAcordou();

    @Query("SELECT * from eventos_table WHERE evento = 'Mamou' ORDER BY data ASC")
    LiveData<List<Evento>> getAllMamou();

    @Query("SELECT * from eventos_table WHERE evento = 'Trocou' ORDER BY data ASC")
    LiveData<List<Evento>> getAllTrocou();

    @Query("SELECT * from eventos_table ORDER BY data DESC LIMIT 1")
    Evento getLastEvento();


}