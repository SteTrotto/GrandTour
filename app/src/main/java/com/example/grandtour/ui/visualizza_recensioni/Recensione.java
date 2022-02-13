package com.example.grandtour.ui.visualizza_recensioni;

public class Recensione {
    private String titoloRecensione;
    private String corpoRecensione;
    private String rating;
    private String regione;
    private String idViaggio;
    private String id_Utente;
    private String nome_Utente;

    //primo costruttore vuoto
    public Recensione() {
    }

    //costruttore pieno
    public Recensione(String titoloRecensione, String corpoRecensione, String rating, String regione, String idViaggio, String id_Utente, String nome_utente) {
        this.titoloRecensione = titoloRecensione;
        this.corpoRecensione = corpoRecensione;
        this.rating = rating;
        this.regione = regione;
        this.idViaggio = idViaggio;
        this.id_Utente = id_Utente;
        this.nome_Utente = nome_utente;
    }

    public String getTitoloRecensione() {
        return titoloRecensione;
    }

    public void setTitoloRecensione(String titoloRecensione) {
        this.titoloRecensione = titoloRecensione;
    }

    public String getCorpoRecensione() {
        return corpoRecensione;
    }

    public void setCorpoRecensione(String corpoRecensione) {
        this.corpoRecensione = corpoRecensione;
    }

    //rating
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    //nome Regione
    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    //id viaggio
    public String getIdViaggio() {
        return idViaggio;
    }

    public void setIdViaggio(String idViaggio) {
        this.idViaggio = idViaggio;
    }

    //id utente
    public String getId_Utente() {return id_Utente; }

    public void setId_Utente(String id_utente) { this.id_Utente = id_utente; }

    //nome utente
    public String getNome_Utente(){ return nome_Utente; }

    public void setNome_Utente (String nome_utente) { this.nome_Utente = nome_utente; }

}
