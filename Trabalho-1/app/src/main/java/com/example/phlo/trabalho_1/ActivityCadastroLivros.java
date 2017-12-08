package com.example.phlo.trabalho_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCadastroLivros extends AppCompatActivity {
    public static final int dado=2;
    private Button btnSalvar;
    private Button btnVoltar;
    private EditText titulo;
    private EditText editora;
    private EditText ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livros);
        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);
        titulo=(EditText)findViewById(R.id.titulo);
        editora=(EditText)findViewById(R.id.editora);
        ano=(EditText)findViewById(R.id.ano);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Titulo=titulo.getText().toString();
                String Editora=editora.getText().toString();
                String Ano=ano.getText().toString();
                if(Titulo.isEmpty()){
                    titulo.requestFocus();
                }
                else if(Editora.isEmpty()){
                    editora.requestFocus();
                }
                else if(Ano.isEmpty()){
                    ano.requestFocus();
                }
                else{
                    Intent enviarDados= new Intent();
                    enviarDados.putExtra("titulo",Titulo);
                    enviarDados.putExtra("editora",Editora);
                    enviarDados.putExtra("ano",Ano);
                    setResult(RESULT_OK,enviarDados);
                    Toast toast =Toast.makeText(getApplicationContext(),"Adicionado com sucesso",Toast.LENGTH_SHORT);
                    toast.show();
                    finish();

                }

            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar= new Intent(ActivityCadastroLivros.this,MainActivity.class);
                startActivity(voltar);
            }
        });

    }
}
