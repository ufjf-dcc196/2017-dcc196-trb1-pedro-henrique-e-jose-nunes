package com.example.phlo.myapplication;

import java.util.ArrayList;

/**
 * Created by phlo on 23/10/17.
 */

public class Reserva {
    private Livro livro;
    private ArrayList<Participante>participantes=new ArrayList<>();

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public ArrayList<Participante> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

    public Reserva(Livro livro, ArrayList<Participante> participantes) {
        this.livro = livro;
        this.participantes = participantes;
    }

    public Reserva(Livro livro,Participante p) {
        this.livro = livro;
        this.participantes.add(p);
    }


    public void AdicionaParticipante(Participante p){
        this.participantes.add(p);

    }

    public void AdicionaArrayParticipante(ArrayList<Participante> participantes){
        this.participantes =participantes;

    }
}
