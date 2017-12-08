package com.example.phlo.trabalho_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityCadastroParticipante extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnVoltar;
    private EditText nome;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);
        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);
        nome=(EditText)findViewById(R.id.nome);
        email=(EditText)findViewById(R.id.email);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
        ArrayList<Participante> p = new ArrayList<>();
            @Override
            public void onClick(View v) {
                String nomes=nome.getText().toString();
                String emails=email.getText().toString();
                if(nomes.isEmpty()){
                    nome.requestFocus();
                }
                else if(emails.isEmpty()){
                    email.requestFocus();
                }
                else
                    {
                    Intent enviarDados= new Intent();
                    enviarDados.putExtra("nome",nomes);
                    enviarDados.putExtra("email",emails);
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
                Intent voltar= new Intent(ActivityCadastroParticipante.this,MainActivity.class);
                startActivity(voltar);
            }
        });
    }
}
