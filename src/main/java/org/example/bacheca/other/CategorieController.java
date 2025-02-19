package org.example.bacheca.other;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.CategorieDAO;
import org.example.bacheca.model.domain.Categoria;
import java.util.ArrayList;
import java.util.List;


public class CategorieController {



    public static void stampaCategorie() {
        List<Categoria> categorie;

        try{
            CategorieDAO dao = new CategorieDAO();
            categorie = dao.execute();

            Printer.printlnBlu("Categorie:");
            stampaGerarchia(null, categorie, "  ");

        } catch (DAOException e){
            Printer.errorPrintln("Errore nel caricamento delle categorie;");
        }

    }


    public static void stampaGerarchia(String madre, List<Categoria> categorie, String indentation) {
        List<Categoria> daStampare = new ArrayList<>();
        String nextIndentation = indentation.concat("    ");

        for (Categoria cat : categorie) {
            if ((cat.getCategoriaMadre() == null && madre == null) ||
                    (cat.getCategoriaMadre() != null && cat.getCategoriaMadre().equals(madre))) {
                daStampare.add(cat);
            }
        }

        for (Categoria cat : daStampare) {
            Printer.println(indentation + ">" + cat.getNomeCategoria());
            stampaGerarchia(cat.getNomeCategoria(), categorie, nextIndentation);
        }
    }


}
