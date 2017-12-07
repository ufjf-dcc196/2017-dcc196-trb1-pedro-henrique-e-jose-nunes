package com.example.phlo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

import java.util.Date;

public class ActivityPessoaDados extends AppCompatActivity {
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_dados);
        StringBuilder sb= new StringBuilder();
        Intent i = getIntent();
        String nome=i.getStringExtra("nome");
        String email=i.getStringExtra("email");

        sb.append("Nome : ");
        sb.append(nome);
        sb.append("\n");
        sb.append("E-mail : ");
        sb.append(email);
        sb.append("\n");

        String dataEntrada=i.getStringExtra("horaEntrada");
        String dataSaida=i.getStringExtra("horaSaida");

        sb.append("Hora Entrada: ");
        if(dataEntrada!=null)
            sb.append(dataEntrada);
        sb.append("\n");
        sb.append("Hora Saida: ");
        if (dataSaida!=null)
            sb.append(dataSaida);
        txt=(TextView)findViewById(R.id.txt);

        txt.setText(sb);
    }
}
