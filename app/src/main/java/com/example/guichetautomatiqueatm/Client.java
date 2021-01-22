package com.example.guichetautomatiqueatm;

import java.io.Serializable;

public  class Client implements Serializable {

    protected   String nom;
    protected   String prenom;
    protected   String username;
    protected  String numeroNIP;

    public Client(String nom, String prenom, String username, String numeroNIP) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.numeroNIP = numeroNIP;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumeroNIP() {return numeroNIP;     }

    public void setNumeroNIP(String numeroNIP) {
        this.numeroNIP = numeroNIP;
    }

    @Override
    public String toString() {
        return "Nom du client  " + nom + "\n" +
                "Prenom du client " + prenom + "\n" ;

    }
}
