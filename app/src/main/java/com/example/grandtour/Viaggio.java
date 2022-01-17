package com.example.grandtour;

public class Viaggio {

    /*
    private String dataPartenza;    //oppure tipo Date
    private String dataRitorno;     //oppure tipo Date
    private String destinazione;
    private String idItinerario;    //oppure tipo Itinerario
    */
    private String mezzo;
    private String regione;
    private String durata;
    private String nomeViaggio;
    private String tappa1;
    private String tappa2;
    private String tappa3;
    private String tappa4;


    public Viaggio(){ }

    public Viaggio(String m, String r, String d, String n, String t1, String t2, String t3) {
        mezzo = m;
        regione = r;
        durata = d;
        nomeViaggio = n;
        tappa1 = t1;
        tappa2 = t2;
        tappa3 = t3;
    }

    public Viaggio(String m, String r, String d, String n, String t1, String t2, String t3, String t4) {
        mezzo = m;
        regione = r;
        durata = d;
        nomeViaggio = n;
        tappa1 = t1;
        tappa2 = t2;
        tappa3 = t3;
        tappa4 = t4;
    }
/*
    public Viaggio(String dP, String dR, String d, String idI, String m, String r) {
        dataPartenza = dP;
        dataRitorno = dR;
        destinazione = d;
        idItinerario = idI;
        mezzo = m;
        regione = r;
    }
    */
/*
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
*/
    public String getMezzo() {
        return mezzo;
    }

    public String getRegione() {
        return regione;
    }

    public String getDurata() {
        return durata;
    }

    public String getNomeViaggio() {
        return nomeViaggio;
    }

    public String getTappa1() {
        return tappa1;
    }

    public String getTappa2() {
        return tappa2;
    }

    public String getTappa3() {
        return tappa3;
    }

    public String getTappa4() {
        return tappa4;
    }

    /*
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
                        */
    public void setMezzo(String mezzo) {
        this.mezzo = mezzo;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public void setNomeViaggio(String nomeViaggio) {
        this.nomeViaggio = nomeViaggio;
    }

    public void setTappa1(String tappa1) {
        this.tappa1 = tappa1;
    }

    public void setTappa2(String tappa2) {
        this.tappa2 = tappa2;
    }

    public void setTappa3(String tappa3) {
        this.tappa3 = tappa3;
    }

    public void setTappa4(String tappa4) {
        this.tappa4 = tappa4;
    }
}
