package com.example.grandtour;

public class Utente {

    private String email;
    private String password;

    public Utente()
    {

    }

    public Utente(String email, String password)
    {
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
