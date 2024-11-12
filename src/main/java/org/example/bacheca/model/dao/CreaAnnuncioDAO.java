package org.example.bacheca.model.dao;

import org.example.bacheca.model.domain.Annuncio;

import java.util.Objects;

public class CreaAnnuncioDAO implements GenericDAO{
    @Override
    public int execute(Objects... params){

        Annuncio annuncio = params[0];
        int id_annuncio;

    }
}
