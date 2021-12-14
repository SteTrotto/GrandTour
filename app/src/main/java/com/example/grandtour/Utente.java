package com.example.grandtour;

public class Utente {

    private String email;
    private String password;
    private String nome_cognome;

    public Utente()
    {

    }

    public Utente(String nome_cognome, String email, String password)
    {
        nome_cognome = this.nome_cognome;
        email = this.email;
        password = this.password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }
}
