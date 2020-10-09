package com.example.monitorbebe.database;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventoViewModel extends AndroidViewModel {

    static private EventoRepository mRepository;

    public EventoViewModel (Application application) {
        super(application);
        mRepository = new EventoRepository(application);
    }

    public static void insert(Evento evento) { mRepository.insert(evento); }
    public static void delete(Evento evento) { mRepository.delete(evento); }
    public static void update(Evento evento) { mRepository.update(evento); }

    public static Evento getLastEvento(){ return mRepository.getLastEvento(); }

    public static LiveData<List<Evento>> getAllEventos() { return mRepository.getAllEventos(); }

    public static LiveData<List<Evento>> getAllDormiu() { return mRepository.getAllDormiu(); }

    public static LiveData<List<Evento>> getAllAcordou() { return mRepository.getAllAcordou(); }

    public static LiveData<List<Evento>> getAllMamou() { return mRepository.getAllMamou(); }

    public static LiveData<List<Evento>> getAllTrocou() { return mRepository.getAllTrocou(); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LiveData<List<Evento>> getToday() { return mRepository.getToday(getDayStart(), getDayEnd()); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Long getDayStart() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"-00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Long getDayEnd(){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"-23:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

}
