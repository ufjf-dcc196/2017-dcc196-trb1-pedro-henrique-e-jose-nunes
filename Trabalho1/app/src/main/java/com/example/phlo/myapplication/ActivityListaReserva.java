package com.example.phlo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityListaReserva extends AppCompatActivity {
    private TextView text;
    private ArrayList<Participante> participa = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reserva);
        StringBuilder sb= new StringBuilder();
        Intent i = getIntent();

        String txtTitulo=i.getStringExtra("titulo");
        String txtEditora=i.getStringExtra("editora");
        int ano=i.getIntExtra("ano",0);
        sb.append("Titulo : ");
        sb.append(txtTitulo);
        sb.append("\n");
        sb.append("Editora : ");
        sb.append(txtEditora);
        sb.append("\n");
        sb.append("Ano : ");
        sb.append(ano);
        sb.append("\n");
        sb.append("Reservas : ");
        ArrayList<Participante> auxParticipante = i.getParcelableArrayListExtra("participante");
            for (int j = 0; j < auxParticipante.size(); j++) {
                participa.add(auxParticipante.get(j));
                sb.append("Participante " + j + " : ");
                sb.append(participa.get(j).getNome());
                sb.append("\n");
            }
         text = (TextView) findViewById(R.id.text);

            text.setText(sb);

    }
}
