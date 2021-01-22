package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Ecran_Administrateur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran__administrateur);

    }

    public void list_Clients_Click(View view) {

        Intent i = new Intent(this, Liste_Clients.class);
        startActivity(i);
    }

    public void list_Cheque_Click(View view) {

        Intent i = new Intent(this, Liste_Comptes_Cheque.class);
        startActivity(i);

    }

    public void list_epargn_Click(View view) {

        Intent i = new Intent(this, Liste_Comptes_Epargne.class);
        startActivity(i);
    }

    public void paiement_interet_click(View view)
    {

        for (int i = 0; i < MainActivity.guichet.comptesEpargne.size(); i++)
        {
            MainActivity.guichet.PaimentClientsInteret();
            Toast.makeText(this,"Le paiement des intérêts sur tous comptes Epargne a été effectué avec succès! " ,Toast.LENGTH_LONG).show();

        }
    }
}