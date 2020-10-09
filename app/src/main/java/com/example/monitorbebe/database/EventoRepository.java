package com.example.monitorbebe.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EventoRepository {
    private EventoDao mEventoDao;
    private Evento lastEvento;

    EventoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mEventoDao = db.eventoDao();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            lastEvento = mEventoDao.getLastEvento();
        });
    }

    LiveData<List<Evento>> getAllEventos() {
        LiveData<List<Evento>> mAllEventos;
        mAllEventos = mEventoDao.getAll();
        return mAllEventos;
    }

    LiveData<List<Evento>> getAllDormiu() {
        LiveData<List<Evento>> mAllDormiu;
        mAllDormiu = mEventoDao.getAllDormiu();
        return mAllDormiu;
    }

    LiveData<List<Evento>> getAllAcordou() {
        LiveData<List<Evento>> mAllAcordou;
        mAllAcordou = mEventoDao.getAllAcordou();
        return mAllAcordou;
    }

    LiveData<List<Evento>> getAllMamou() {
        LiveData<List<Evento>> mAllMamou;
        mAllMamou = mEventoDao.getAllMamou();
        return mAllMamou;
    }

    LiveData<List<Evento>> getAllTrocou() {
        LiveData<List<Evento>> mAllTrocou;
        mAllTrocou = mEventoDao.getAllTrocou();
        return mAllTrocou;
    }

    LiveData<List<Evento>> getToday(Long daystart, Long dayend) {
        LiveData<List<Evento>> mAllToday;
        mAllToday = mEventoDao.getToday(daystart, dayend);
        return mAllToday;
    }

    Evento getLastEvento() {
        return lastEvento;
    }

    void insert(Evento evento) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mEventoDao.insert(evento);
        });
        lastEvento = evento;
    }

    void delete(Evento evento){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mEventoDao.delete(evento);
        });
    }

    void update(Evento evento){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mEventoDao.update(evento);
        });
    }
}
