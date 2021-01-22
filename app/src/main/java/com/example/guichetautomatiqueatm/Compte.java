package com.example.guichetautomatiqueatm;

import android.widget.Toast;

import androidx.annotation.NonNull;

public class Compte {

    protected String numeroNIP;
    protected String numeroCompte;
    protected  double soldeCompte;

    public Compte()
    {}

    public Compte(String numeroNIP, String numeroCompte, double soldeCompte) {
        this.numeroNIP = numeroNIP;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = soldeCompte;
    }


    //Méthode de dépôt qui retourne le solde mis a jour
    public double depot(double montant)
    {
        soldeCompte =soldeCompte+ montant;
        return soldeCompte;
    }

    //Méthode de retrait qui retourne le solde mis a jour
    public double retrait(double montant)
    {
      soldeCompte =soldeCompte- montant;
        return soldeCompte;
    }


    public String getNumeroNIP() {
        return numeroNIP;
    }

    public void setNumeroNIP(String numeroNIP) {
        this.numeroNIP = numeroNIP;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getSoldeCompte() { return soldeCompte;     }

    public void setSoldeCompte(double soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "numeroNIP='" + numeroNIP + '\'' +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", soldeCompte=" + soldeCompte +
                '}';
    }
}
