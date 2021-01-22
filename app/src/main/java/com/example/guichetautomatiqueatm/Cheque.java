package com.example.guichetautomatiqueatm;

import androidx.annotation.NonNull;

public class Cheque extends Compte{

    public Cheque(String numeroNIP, String numeroCompte, double soldeCompte) {
        super(numeroNIP, numeroCompte, soldeCompte);
    }


    public Cheque() {
    }

    @Override
    public String toString() {
        return "Cheque{}";
    }
}
