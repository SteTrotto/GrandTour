package com.example.grandtour;

import java.util.Date;

public class Utente {

    private String email;
    private String password;
    private String nome_cognome;
    private String Data_nascità;

    public Utente()
    {

    }

    public Utente(String nome_cognome, String email, String password, String Data_nascità)
    {
        nome_cognome = this.nome_cognome;
        email = this.email;
        password = this.password;
        Data_nascità = this.Data_nascità;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public String getNome_cognome() { return nome_cognome; }

    public String getData_nascità() { return Data_nascità; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setNome_cognome(String nome_cognome) { this.nome_cognome = nome_cognome; }

    public void setData_nascità(String data_nascità) { Data_nascità = data_nascità; }

}
