package com.example.grandtour;

public class Itinerario {

    private String[] tappa;
    private boolean breve;  //ogni tipo va bene basta che si riconosce il tipo di tappa
                            //tipo = true -> breve         tipo = false -> lungo
                            //volendo la senza variabile in più si possono definire
                            // il tipo dalla lunghezza del vettore

    public Itinerario() {


    }

    public Itinerario(String[] t, boolean b) {
        breve = b;
        if(breve)
            tappa = new String[3];
        else
            tappa = new String[5];
        for(int i = 0; i < t.length; i++) {
            tappa[i] = t[i];
        }
    }

    public String[] getTappa() {
        return tappa;
    }

    public boolean isBreve() {
        return breve;
    }

    public void setTappa(String[] tappa) {
        if(this.tappa == null) this.tappa = new String[tappa.length];    //se vettore vuoto, inserisce la lungezza
        if(this.tappa.length != tappa.length) return;   //lunghezza diversa -> non compatibili
        for(int i = 0; i < tappa.length; i++) {
            this.tappa[i] = tappa[i];
        }
    }

    public void setBreve(boolean breve) {
        this.breve = breve;
    }
}
