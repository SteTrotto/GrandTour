package com.example.grandtour.ui.visualizza_recensioni;

public class Recensione {
    private String titoloRecensione;
    private String corpoRecensione;
    private String rating;
    private String regione;
    private String idViaggio;
    //private String utente;
    //private String data;

    //primo costruttore vuoto
    public Recensione() {
    }

    //costruttore pieno
    public Recensione(String titoloRecensione, String corpoRecensione, String rating, String regione, String idViaggio) {
        this.titoloRecensione = titoloRecensione;
        this.corpoRecensione = corpoRecensione;
        this.rating = rating;
        this.regione = regione;
        this.idViaggio = idViaggio;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getIdViaggio() {
        return idViaggio;
    }

    public void setIdViaggio(String idViaggio) {
        this.idViaggio = idViaggio;
    }
}
