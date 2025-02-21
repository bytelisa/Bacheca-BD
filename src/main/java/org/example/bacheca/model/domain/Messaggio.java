package org.example.bacheca.model.domain;
import java.sql.Timestamp;
import java.time.LocalDateTime;
public class Messaggio {

    //classe che rappresenta messaggi privati e commenti pubblici


    /*Timestamp timestamp = resultSet.getTimestamp("data_creazione");
    LocalDateTime dataLocale = timestamp.toLocalDateTime();
*/
    private String mittente;
    private String destinatario;
    private String contenuto;
    private TipoMessaggio tipoMessaggio;
    private int idAnnuncioRelativo;
    private int id;
    private Timestamp dataOra;


    public Messaggio(String mitt, String dest, String cont, TipoMessaggio tipo, int idA) {
        this.mittente=mitt;
        this.destinatario=dest;
        this.contenuto=cont;
        this.tipoMessaggio=tipo;
        this.idAnnuncioRelativo=idA;
    }

    public Messaggio(String mitt, String dest, String cont, TipoMessaggio tipo, int idA, int id, Timestamp time) {
        this.mittente=mitt;
        this.destinatario=dest;
        this.contenuto=cont;
        this.tipoMessaggio=tipo;
        this.idAnnuncioRelativo=idA;
        this.id =id;
        this.dataOra=time;
    }


    public String getMittente() {
        return mittente;
    }

    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public TipoMessaggio getTipoMessaggio() {
        return tipoMessaggio;
    }

    public void setTipoMessaggio(TipoMessaggio tipoMessaggio) {
        this.tipoMessaggio = tipoMessaggio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnnuncioRelativo() {
        return idAnnuncioRelativo;
    }

    public void setIdAnnuncioRelativo(int idAnnuncioRelativo) {
        this.idAnnuncioRelativo = idAnnuncioRelativo;
    }

    public Timestamp getDataOra() {
        return dataOra;
    }

    public void setDataOra(Timestamp dataOra) {
        this.dataOra = dataOra;
    }
}
