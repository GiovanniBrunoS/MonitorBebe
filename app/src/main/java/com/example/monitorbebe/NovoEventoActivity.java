package com.example.monitorbebe;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorbebe.database.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class NovoEventoActivity extends AppCompatActivity {

    EditText dataEvento;
    EditText horaEvento;
    Spinner tipoEvento;
    Evento evento;
    int requestCode;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_evento);

        dataEvento = findViewById(R.id.data_evento);
        horaEvento = findViewById(R.id.hora_evento);
        tipoEvento = findViewById(R.id.tipo_evento);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.eventos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoEvento.setAdapter(adapter);


        Bundle bundle = getIntent().getExtras();
        requestCode = bundle != null ? bundle.getInt("request_code") : 0;

        if (requestCode == MainActivity.REQUEST_EDITAR_EVENTO) {
            evento = (Evento) bundle.getSerializable("evento");

            dataEvento.setText(new SimpleDateFormat("dd/MM/yyyy").format(evento.getData()));
            horaEvento.setText(new SimpleDateFormat("HH:mm").format(evento.getData()));
            setSpinText(evento.getTipoEvento());
        } else {
            dataEvento.setText(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            horaEvento.setText(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("HH:mm")));
            evento = new Evento();
        }
    }

    public void concluir(View view) throws ParseException {

        if(requestCode == MainActivity.REQUEST_EDITAR_EVENTO){
            if(evento.getTipoEvento().equals("Acordou") || evento.getTipoEvento().equals("Dormiu")){
                new AlertDialog.Builder(this)
                        .setMessage("Isso pode causar incosistência nos resumos, deseja continuar?")
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                                Bundle bundle = new Bundle();
                            try {
                                evento.setData(new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(dataEvento.getText().toString() +"-" + horaEvento.getText().toString()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            evento.setTipoEvento(tipoEvento.getSelectedItem().toString());
                                bundle.putSerializable("evento", evento);
                                Intent returnIntent = new Intent();
                                returnIntent.putExtras(bundle);
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                        })
                        .setNegativeButton(android.R.string.no, (dialog, whichButton) -> {
                                Bundle bundle = new Bundle();
                            try {
                                evento.setData(new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(dataEvento.getText().toString() +"-" + horaEvento.getText().toString()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            evento.setTipoEvento(tipoEvento.getSelectedItem().toString());
                                bundle.putSerializable("evento", evento);
                                Intent returnIntent = new Intent();
                                returnIntent.putExtras(bundle);
                                setResult(Activity.RESULT_CANCELED, returnIntent);
                                finish();
                        }).show();
            }else{
                Bundle bundle = new Bundle();
                evento.setData(new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(dataEvento.getText().toString() +"-" + horaEvento.getText().toString()));
                evento.setTipoEvento(tipoEvento.getSelectedItem().toString());
                bundle.putSerializable("evento", evento);
                Intent returnIntent = new Intent();
                returnIntent.putExtras(bundle);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }else{
            Bundle bundle = new Bundle();
            evento.setData(new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(dataEvento.getText().toString() +"-" + horaEvento.getText().toString()));
            evento.setTipoEvento(tipoEvento.getSelectedItem().toString());
            bundle.putSerializable("evento", evento);
            Intent returnIntent = new Intent();
            returnIntent.putExtras(bundle);

            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    public void setSpinText(String text)
    {
        for(int i= 0; i < tipoEvento.getAdapter().getCount(); i++)
        {
            if(tipoEvento.getAdapter().getItem(i).toString().contains(text))
            {
                tipoEvento.setSelection(i);
            }
        }

    }
}
