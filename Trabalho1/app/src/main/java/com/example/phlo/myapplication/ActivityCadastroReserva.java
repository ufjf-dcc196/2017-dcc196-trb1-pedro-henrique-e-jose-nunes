package com.example.phlo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityCadastroReserva extends AppCompatActivity {

    private ArrayList<Livro> livros=new ArrayList<>();
    private EditText editNome;
    private Button btnSalvar;
    private Button btnVoltar;
    private ListView listLivro;
    private ArrayList<Participante> participa= new ArrayList<>();
    private Livro reservar;
    private Reserva reservarLivro;
    private ArrayList<Reserva> reservardelivros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);

        //alimenta livros
        Intent intent= getIntent();
        ArrayList<Livro> auxlivro= intent.getParcelableArrayListExtra("dados");
       for(int i=0;i<auxlivro.size();i++){
           livros.add(auxlivro.get(i));
       }

        //Cria Participante
        final ArrayList<Participante> auxParticipante= intent.getParcelableArrayListExtra("participante");
        for(int i=0;i<auxParticipante.size();i++){
            participa.add(auxParticipante.get(i));
        }
        //Alimenta Reserva

            reservardelivros.add(new Reserva(livros.get(0),participa.get(0)));
            reservardelivros.add(new Reserva(livros.get(1),participa.get(1)));
            reservardelivros.get(0).AdicionaParticipante(participa.get(1));
            reservardelivros.get(1).AdicionaParticipante(participa.get(0));
        //cria listview
        listLivro=(ListView)findViewById(R.id.listLivro);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,titulos(livros));
        listLivro.setAdapter(adapter);
        listLivro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                reservar=livros.get(position);

                Toast toast =Toast.makeText(getApplicationContext(),livros.get(position).getTitulo()+
                        " foi selecionado",Toast.LENGTH_SHORT);
                toast.show();
                return true;
            }
        });
        listLivro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent enviarDados= new Intent(ActivityCadastroReserva.this,ActivityListaReserva.class);
                enviarDados.putExtra("titulo",livros.get(position).getTitulo());
                enviarDados.putExtra("editora",livros.get(position).getEditora());
                enviarDados.putExtra("ano",livros.get(position).getAno());

                System.out.println("numero de participantes"+reservardelivros.get(position).getParticipantes().size());
                enviarDados.putParcelableArrayListExtra("participante",reservardelivros.get(position).getParticipantes());
                startActivity(enviarDados);
            }
        });


        editNome=(EditText)findViewById(R.id.editNome);


        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar= new Intent(ActivityCadastroReserva.this,MainActivity.class);
                startActivity(voltar);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editNome.getText().toString();
                if(Contem(participa,name)) {
                    if (reservar != null) {
                        Participante p;
                        p = Part(participa, name);
                        if (indiceLivroReservado(reservardelivros, reservar.getTitulo()) == -1) {
                            reservardelivros.add(new Reserva(reservar, p));
                        } else {

                            int indiceLivro = indiceLivroReservado(reservardelivros, reservar.getTitulo());

                            reservardelivros.get(indiceLivro).AdicionaParticipante(p);
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Livro nao foi selecionado", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else{
                    editNome.requestFocus();
                    editNome.selectAll();
                    Toast toast =Toast.makeText(getApplicationContext(),"Nome nao esta na base de dados",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    public ArrayList<String> titulos(ArrayList<Livro> l ){
        ArrayList<String> titulo = new ArrayList<>();
        for (int i=0;i<l.size();i++){
            titulo.add(l.get(i).getTitulo());
        }
        return titulo;
    }
    public boolean Contem (ArrayList<Participante> p,String nome){
        for (int i=0;i<p.size();i++){

            if (p.get(i).getNome().equals(nome)) {
                return true;
            }
            }
        return false;
    }
    public int indiceLivroReservado(ArrayList<Reserva> r ,String nome){
        for (int i=0;i<r.size();i++) {
            if (r.get(i).getLivro().getTitulo().equals(nome))
                return i;
        }
            return -1;
    }
    public Participante Part (ArrayList<Participante> p,String nome){
        for (int i=0;i<p.size();i++){

            if (p.get(i).getNome().equals(nome)) {
                return p.get(i);
            }
        }
        return null;
    }

}
