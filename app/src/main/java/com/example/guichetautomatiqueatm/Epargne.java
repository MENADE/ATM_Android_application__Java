package com.example.guichetautomatiqueatm;

import androidx.annotation.NonNull;

public class Epargne extends Compte {

 private final double tauxInteret = 0.0125;

    public Epargne(String numeroNIP, String numeroCompte, double soldeCompte) {
        super(numeroNIP, numeroCompte, soldeCompte);
    }

    public Epargne() {
    }

    //Méthode de paiement des intérêts pour tous les comptes
    public double  paiementInteret()
    {
        soldeCompte =soldeCompte- soldeCompte *getTauxInteret();
        return soldeCompte;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public String toString() {
        return "Epargne{" +
                "tauxInteret=" + tauxInteret +
                '}';
    }
}
