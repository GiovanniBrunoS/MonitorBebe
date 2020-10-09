package com.example.monitorbebe;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.monitorbebe.database.Evento;
import com.example.monitorbebe.database.EventoViewModel;
import com.example.monitorbebe.lists.acordou.AcordouAdapter;
import com.example.monitorbebe.lists.dormiu.DormiuAdapter;
import com.example.monitorbebe.lists.hoje.HojeAdapter;
import com.example.monitorbebe.lists.mamou.MamouAdapter;
import com.example.monitorbebe.lists.resumo.ResumoAdapter;
import com.example.monitorbebe.lists.trocou.TrocouAdapter;
import com.example.monitorbebe.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    public static final int REQUEST_EDITAR_EVENTO = 1;
    public HojeAdapter hojeAdapter;
    private DormiuAdapter dormiuAdapter;
    private AcordouAdapter acordouAdapter;
    private MamouAdapter mamouAdapter;
    private ResumoAdapter resumoAdapter;
    private TrocouAdapter trocouAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventoViewModel eventoViewModel = new ViewModelProvider(this).get(EventoViewModel.class);

        this.hojeAdapter = new HojeAdapter(this);
        this.dormiuAdapter = new DormiuAdapter(this);
        this.acordouAdapter = new AcordouAdapter(this);
        this.mamouAdapter = new MamouAdapter(this);
        this.resumoAdapter = new ResumoAdapter();
        this.trocouAdapter = new TrocouAdapter(this);

        eventoViewModel.getToday().observe(this,
                eventos -> hojeAdapter.setEventos(eventos));

        eventoViewModel.getAllEventos().observe(this,
                eventos -> resumoAdapter.setEventos(eventos));

        eventoViewModel.getAllDormiu().observe(this,
                eventos -> dormiuAdapter.setEventos(eventos));

        eventoViewModel.getAllAcordou().observe(this,
                eventos -> acordouAdapter.setEventos(eventos));

        eventoViewModel.getAllMamou().observe(this,
                eventos -> mamouAdapter.setEventos(eventos));

        eventoViewModel.getAllTrocou().observe(this,
                eventos -> trocouAdapter.setEventos(eventos));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), hojeAdapter, dormiuAdapter, acordouAdapter, mamouAdapter, resumoAdapter, trocouAdapter);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);
    }

    public void newEvento(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("request_code", 0);
        Intent intent = new Intent(this, NovoEventoActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_EDITAR_EVENTO){
            Bundle bundle = data.getExtras();
            Evento evento = (Evento) bundle.getSerializable("evento");
            EventoViewModel.update(evento);
        }
        else if(resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            Evento evento = (Evento) bundle.getSerializable("evento");

            Evento lastEvento = EventoViewModel.getLastEvento();
            if(lastEvento != null ) {
                if (lastEvento.getTipoEvento().equals("Dormiu") && !evento.getTipoEvento().equals("Acordou")) {
                    Evento acordouEvento = new Evento(evento.getData(), "Acordou");
                    EventoViewModel.insert(acordouEvento);
                }
            }

            EventoViewModel.insert(evento);
        }
    }

}
