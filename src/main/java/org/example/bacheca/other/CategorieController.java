package org.example.bacheca.other;

import org.example.bacheca.exception.DAOException;
import org.example.bacheca.model.dao.CategorieDAO;
import org.example.bacheca.model.domain.Categoria;
import java.util.List;

public class CategorieController {



    public static void stampaCategorie() {
        List<Categoria> categorie;

        try{
            CategorieDAO dao = new CategorieDAO();
            categorie = dao.execute();

            Printer.printlnBlu("Categorie:");
            stampaGerarchia(null, categorie);

        } catch (DAOException e){
            Printer.errorPrintln("Errore nel caricamento delle categorie;");
        }

    }

    public static void stampaGerarchia(String madre, List<Categoria> categorie) {

        for (Categoria cat: categorie) {

            if (cat.getCategoriaMadre() == madre) {
                Printer.println("   >" + cat.getNomeCategoria());
                Printer.println("           >");
                stampaGerarchia(cat.getNomeCategoria(), categorie);
            }
        }
    }

}
