package com.example.phlo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int Nome=1;
    public static final int titulo=2;
    private Button btnCadastroParticipante;
    private Button btnReservar;
    private Button btnCadastroLivro;
    private ArrayList<Participante> participa= new ArrayList<>();
    private ArrayList<Livro> livro= new ArrayList<>();
    private ListView participantes;

    private static int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastroLivro=(Button)findViewById(R.id.btnCadastroLivro);
        btnCadastroParticipante=(Button)findViewById(R.id.btnCadastroParti);
        btnReservar=(Button)findViewById(R.id.btnReserva);
        //Cria lalguns participantes
        Participante p1 = new Participante("Pedro","phlo.jf@hotmail.com");
        Participante p2 = new Participante("Perrela","exemplo@gmail.com");
        participa.add(p1);
        participa.add(p2);

        Livro l1= new Livro("A descoberta de Platao","Lemos",1996);
        Livro l2= new Livro("Marte o planeta vermelho","Lemos",2005);
        livro.add(l1);
        livro.add(l2);
        //alimenta o listview participantes
        participantes=(ListView)findViewById(R.id.participantes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,nomes(participa));
        participantes.setAdapter(adapter);

        participantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Date data = new Date();
                Calendar cal = Calendar.getInstance();
                data = cal.getTime();

                if(participa.get(position).getHoraEntrada()==null) {
                    participa.get(position).setHoraEntrada(data);
                    return true;

                }
                if(participa.get(position).getHoraSaida()==null) {
                    participa.get(position).setHoraSaida(data);
                    return true;
                }
                else  {
                    participa.get(position).setHoraEntrada(null);
                    participa.get(position).setHoraSaida(null);
                    return true;
                }

            }
        });
        participantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dadosSobre= new Intent(MainActivity.this,ActivityPessoaDados.class);
                dadosSobre.putExtra("nome",participa.get(position).getNome());
                dadosSobre.putExtra("email",participa.get(position).getEmail());
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");

                if(participa.get(position).getHoraEntrada()!=null) {


                    String dataEntrada = dateFormat.format(participa.get(position).getHoraEntrada());
                    dadosSobre.putExtra("horaEntrada", dataEntrada);

                }
                if(participa.get(position).getHoraSaida()!=null) {
                    String dataSaida = dateFormat.format(participa.get(position).getHoraSaida());
                    dadosSobre.putExtra("horaSaida", dataSaida);
                }

                startActivity(dadosSobre);
            }
        });
        btnCadastroParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cadastrarParti = new Intent(MainActivity.this,ActivityCadastroParticipante.class);
                startActivityForResult(cadastrarParti,Nome);

            }
        });
        btnCadastroLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarLivro = new Intent(MainActivity.this,ActivityCadastroLivros.class);

                startActivityForResult(cadastrarLivro,titulo);
            }
        });
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Reservar = new Intent(MainActivity.this,ActivityCadastroReserva.class);

                    Reservar.putParcelableArrayListExtra("dados", livro);
                    Reservar.putParcelableArrayListExtra("participante",participa);
                startActivity(Reservar);
            }
        });
    }
    public ArrayList<String> nomes(ArrayList<Participante> p ){
        ArrayList<String> nome = new ArrayList<>();
        for (int i=0;i<p.size();i++){
            nome.add(p.get(i).getNome());
        }
        return nome;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==MainActivity.Nome && resultCode==RESULT_OK && data!= null){
            String nome = data.getStringExtra("nome");
            String email = data.getStringExtra("email");
            participa.add(new Participante(nome,email));
            // atualiza o listView
            participantes=(ListView)findViewById(R.id.participantes);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,nomes(participa));
            participantes.setAdapter(adapter);

        }
        if(requestCode==MainActivity.titulo && resultCode==RESULT_OK && data!= null){
            String titulo = data.getStringExtra("titulo");
            String editora = data.getStringExtra("editora");
            String ano = data.getStringExtra("ano");
            livro.add(new Livro(titulo,editora,Integer.parseInt(ano)));


        }
    }
}
