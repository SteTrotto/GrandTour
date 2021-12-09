package com.example.grandtour;

public class Recensione {

    private String destinazioneRecensione;
    private String idItinerario;    //oppure tipo Itinerario
    private String idViaggio;       //oppure tipo Viaggio
    private float rating;
    private String titoloRecensione;
    private String utente;          //oppure tipo User

    public Recensione() {

    }

    public Recensione(String dR, String idI, String idV, float r, String tR, String u) {
        destinazioneRecensione = dR;
        idItinerario = idI;
        idViaggio = idV;
        rating = r;
        titoloRecensione = tR;
        utente = u;
    }



    public String getDestinazioneRecensione() {
        return destinazioneRecensione;
    }

    public String getIdItinerario() {
        return idItinerario;
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

    public String getUtente() {
        return utente;
    }

    public void setDestinazioneRecensione(String destinazioneRecensione) {
        this.destinazioneRecensione = destinazioneRecensione;
    }

    public void setIdItinerario(String idItinerario) {
        this.idItinerario = idItinerario;
    }

    public void setIdViaggio(String idViaggio) {
        this.idViaggio = idViaggio;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setTitoloRecensione(String titoloRecensione) {
        this.titoloRecensione = titoloRecensione;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }
}
