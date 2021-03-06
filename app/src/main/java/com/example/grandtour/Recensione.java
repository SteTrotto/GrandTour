package com.example.grandtour;

public class Recensione {

    private String Regione;    //Regione
    private String idViaggio;       //oppure tipo Viaggio >3 giorni o >5 giorni
    private float rating;
    private String titoloRecensione;
    private String corpoRecensione;
    private String utente;

    public Recensione() {

    }

    public Recensione(String dR, String idV, float r, String tR,String c) {
        Regione = dR;
        idViaggio = idV;
        rating = r;
        titoloRecensione = tR;
        corpoRecensione=c;
    }



    public String getRegione() {
        return Regione;
    }

    public String getIdViaggio() {
        return idViaggio;
    }

    public float getRating() {
        return rating;
    }

    public String getTitoloRecensione() {
        return titoloRecensione;
    }

    public String getCorpoRecensione() {
        return corpoRecensione;
    }

    public String getUtente() {
        return utente;
    }



    public void setRegione(String destinazioneRecensione) {
        this.Regione = destinazioneRecensione;
    }

    public void setIdViaggio(String idViaggio) {
        this.idViaggio = idViaggio;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setTitoloRecensione(String titoloRecensione) { this.titoloRecensione = titoloRecensione;}

    public void setCorpoRecensione(String corpoRecensione)
    {
        this.corpoRecensione=corpoRecensione;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }
}
