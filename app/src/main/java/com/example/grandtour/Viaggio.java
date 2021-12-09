package com.example.grandtour;

public class Viaggio {

    private String dataPartenza;    //oppure tipo Date
    private String dataRitorno;     //oppure tipo Date
    private String destinazione;
    private String idItinerario;    //oppure tipo Itinerario
    private String mezzo;
    private String regione;

    public Viaggio(){

    }

    public Viaggio(String dP, String dR, String d, String idI, String m, String r) {
        dataPartenza = dP;
        dataRitorno = dR;
        destinazione = d;
        idItinerario = idI;
        mezzo = m;
        regione = r;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public String getDataRitorno() {
        return dataRitorno;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public String getIdItinerario() {
        return idItinerario;
    }

    public String getMezzo() {
        return mezzo;
    }

    public String getRegione() {
        return regione;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public void setDataRitorno(String dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public void setIdItinerario(String idItinerario) {
        this.idItinerario = idItinerario;
    }

    public void setMezzo(String mezzo) {
        this.mezzo = mezzo;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
}
