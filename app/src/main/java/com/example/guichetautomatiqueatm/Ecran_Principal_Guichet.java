package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Ecran_Principal_Guichet extends AppCompatActivity {

    TextView txtNomComplet;
    EditText txtMontant;
    String monDigit = "";
    RadioButton radDepot;
    RadioButton radRetrait;
    RadioButton radVirement;
    RadioButton radCheque;
    RadioButton radEpargne;
    String NIP;
    TextView txtResult;
    double currentSoldCheque;
    double currentSoldEpargne;

    Client clientModel;


    //Clé pour recupérer l'objet de type Client clientModel transférer depuis MainActivity
    public static final String EXTRA_DATA = "EXTRA_DATA";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran__principal__guichet);

        // Bronchement des variables avec leurs IDs respectifs
        txtNomComplet = findViewById(R.id.txtNomPrenom);
        txtResult = findViewById(R.id.txtResult);
        txtMontant = findViewById(R.id.txtMontant);

        radDepot = findViewById(R.id.rbDepot);
        radRetrait = findViewById(R.id.rbRetrait);
        radVirement = findViewById(R.id.rbVirement);
        radCheque = findViewById(R.id.rbCheque);
        radEpargne = findViewById(R.id.rbEpargne);

        //Mettre radioButton Cheque et Dépôt activé
        radCheque.setChecked(true);
        radDepot.setChecked(true);

        //Recupération de l'objet clientModel envoyé depuis MainActivity
        clientModel = (Client) getIntent().getSerializableExtra(EXTRA_DATA);
        assert clientModel != null;
        NIP = clientModel.getNumeroNIP();

        //Affichage du message Bonjour + nom + prenom dans  Ecran_principal_Guichet
        txtNomComplet.setText("Bonjour " + clientModel.getNom() + " " + clientModel.getPrenom() + " ");
    }


    //Méthode de recupération du montant de transaction saisie au clavier par l'utilisateur
    public void getDigit_click(View view) {

        Button clickedButton = (Button) view;

        if (txtMontant.getText().toString().isEmpty()) {
            monDigit = "";
        }
        monDigit += clickedButton.getText().toString();
        txtMontant.setText(monDigit);
    }

    // Méthode d'effacer le champ de saisie du montant de transaction le famous 'C' du clavier
    public void clean_montant_click(View view) {
        txtMontant.getText().clear();

    }


   // Méthode d'affichage de l'etat de compte du client : solde compte Cheque et solde compte Epargne
    @SuppressLint("SetTextI18n")
    public void etat_comptes_click(View view) {
        {

            for (int i = 0; i < MainActivity.guichet.comptesCheque.size(); i++) {

                if (MainActivity.guichet.comptesCheque.get(i).getNumeroNIP().equals(NIP))
                    currentSoldCheque = MainActivity.guichet.comptesCheque.get(i).getSoldeCompte();
            }

            for (int i = 0; i < MainActivity.guichet.comptesEpargne.size(); i++) {

                if (MainActivity.guichet.comptesEpargne.get(i).getNumeroNIP().equals(NIP))
                    currentSoldEpargne = MainActivity.guichet.comptesEpargne.get(i).getSoldeCompte();
            }

            // Affichage de l'etat de compte Cheque et compte epargne
             String messageCheque = getResources().getString(R.string.cleSoldCheque);
            String messageEpargne = getResources().getString(R.string.cleSoldEpargne);
            txtResult.setText(messageCheque + " " + currentSoldCheque + "\n" + messageEpargne + " " + currentSoldEpargne);

        }

    }

    // Méthode d'affichage de transaction après avoir saisie son montant et choisie le type et les comptes sur lesquels portera la transaction
    public void valid_Ops_click(View view) {

        if (txtMontant.getText().toString().isEmpty())
            Toast.makeText(this, " Le  montant de transaction est oligatoire!", Toast.LENGTH_SHORT).show();

        else {

            double montantTransfer = Double.parseDouble(txtMontant.getText().toString());
            double result = 0;

            if (montantTransfer == 0)
                ValideTransaction(montantTransfer);

            else {
                // 1- Affichage de transaction : dépôt sur le compte cheque
                if (radCheque.isChecked() && radDepot.isChecked()) {
                  MainActivity.guichet.DepotCheque(NIP, montantTransfer);
                    Toast.makeText(this, "Dépot de " + montantTransfer + " sur votre compte Cheque ", Toast.LENGTH_SHORT).show();
                }

                //2-  Affichage de transaction : dépôt sur le compte épargne
                if (radEpargne.isChecked() && radDepot.isChecked()) {
                     MainActivity.guichet.DepotEpargne(NIP, montantTransfer);
                    Toast.makeText(this, "Dépot de " + montantTransfer + " sur votre compte Epargne ", Toast.LENGTH_SHORT).show();
                }

                //3 Affichage de transaction : retrait sur le compte cheque
                if (radCheque.isChecked() && radRetrait.isChecked()) {

                    ValideTransaction(montantTransfer);
                }


                // 4  Affichage de transaction : retrait sur le compte épargne
                if (radEpargne.isChecked() && radRetrait.isChecked()) {
                    ValideTransaction(montantTransfer);
                }

                // 5  Affichage de transaction : virement vers compte epargne

                if (radEpargne.isChecked() && radVirement.isChecked()) {
                    ValideTransaction(montantTransfer);

                }

                //6  Affichage de transaction : virement vers compte cheque

                if (radCheque.isChecked() && radVirement.isChecked()) {

                    ValideTransaction(montantTransfer);
                }
                }
            }

        }


// 1 Méthode de vérification de validité de la transaction
//2 soumission de la transaction
//3 Etablir le toast ***PERSONNALISÉ** avec le type de transaction et le type de compte sur lequel la transaction a été réalisée

    public void ValideTransaction(double mont) {


        if (mont == 0)
            Toast.makeText(this, "Le Montant doit être differents de 0!", Toast.LENGTH_SHORT).show();


        for (int i = 0; i < MainActivity.guichet.clients.size(); i++) {

            if (MainActivity.guichet.clients.get(i).getNumeroNIP().equals(NIP)) {


                // Vérification si le montant de transaction est multiple de 10 + affichage de toast personnalisé

                if (!((mont % 10) == 0)) {
                    Toast.makeText(this, "Transaction rejetée !  le montant de retrait doit etre multiple de 10 ", Toast.LENGTH_SHORT).show();
                    break;
                }


                // Vérification si le montant de transaction pour le retrait est superieur a 1000 + affichage de toast personnalisé

                if (((radCheque.isChecked() && radRetrait.isChecked() || (radEpargne.isChecked() && radRetrait.isChecked())) && (mont > 1000))) {
                    Toast.makeText(this, "Transaction rejetée !  le montant de retrait ne doit pas dépasser  1000 ", Toast.LENGTH_SHORT).show();
                    break;
                }


                // Vérification si le montant de retrait sur le compte cheque est inférieur ou non a son solde + affichage de toast personnalisé
                if (radCheque.isChecked() && radRetrait.isChecked()) {


                    if ((MainActivity.guichet.comptesCheque.get(i).getSoldeCompte() - mont) < 0) {
                        Toast.makeText(this, "Transaction rejetée !  le montant de retrait est superiuer a la balance de votre compte cheque ", Toast.LENGTH_LONG).show();
                    } else if (MainActivity.guichet.comptesCheque.get(i).getNumeroNIP().equals(NIP)) {
                        Toast.makeText(this, "Retrait de " + mont + " sur votre compte Cheque ", Toast.LENGTH_LONG).show();
                        MainActivity.guichet.RetraitCheque(NIP, mont);
                    }

                }


                // Vérification si le montant de retrait sur le compte Epargne est inférieur ou non a son solde + affichage de toast personnalisé
                if (radEpargne.isChecked() && radRetrait.isChecked()) {

                    if ((MainActivity.guichet.comptesEpargne.get(i).getSoldeCompte() - mont) < 0) {
                        Toast.makeText(this, "Transaction rejetée !  le montant de retrait est superiuer a la balance de votre compte epargne ", Toast.LENGTH_LONG).show();
                    } else if (MainActivity.guichet.comptesEpargne.get(i).getNumeroNIP().equals(NIP)) {
                        Toast.makeText(this, "Retrait de " + mont + " sur votre compte Epargne ", Toast.LENGTH_LONG).show();
                        MainActivity.guichet.RetraitEpargne(NIP, mont);
                    }

                }


                // Vérification si le montant de transaction est supérieur a 100000 + affichage de toat personnalisé

                if (((radEpargne.isChecked() && radVirement.isChecked()) || (radCheque.isChecked() && radVirement.isChecked())) && (mont > 100000))
                {
                    Toast.makeText(this, "Transaction rejetée !  le montant de virement ne doit pas dépasser  100,000 ", Toast.LENGTH_SHORT).show();
                }


                    // Vérification si le montant de virement vers le compte Cheque est superieur au solde de compte Epargne, et affichage de toast personnalisé

                    else if (radCheque.isChecked() && radVirement.isChecked()) {

                        if ((MainActivity.guichet.comptesEpargne.get(i).getSoldeCompte() - mont) < 0) {
                            Toast.makeText(this, "Transaction rejetée !  le montant de virement est superiuer a la balance de votre compte epargne ", Toast.LENGTH_SHORT).show();

                        } else {
                            MainActivity.guichet.VirementCheque(NIP, mont);
                            Toast.makeText(this, "Virement de " + mont + " de votre compte Epargne vers votre compte Cheque ", Toast.LENGTH_SHORT).show();
                        }
                    }


                // Vérification si le montant de virement vers le compte Epargne est superieur au solde de compte cheque, et affichage de toast personnalisé
                else

                    if (radEpargne.isChecked() && radVirement.isChecked()) {

                        if ((MainActivity.guichet.comptesCheque.get(i).getSoldeCompte() - mont) < 0) {
                            Toast.makeText(this, "Transaction rejetée !  le montant de virement est superiuer a la balance de votre compte cheque ", Toast.LENGTH_SHORT).show();

                        } else {
                            MainActivity.guichet.VirementEpargne(NIP, mont);
                            Toast.makeText(this, "Virement de " + mont + " de votre compte Epargne vers votre compte Cheque ", Toast.LENGTH_SHORT).show();
                        }
                    }







            }
        }

    }


//Méthode de Log-out
    public void logout_click (View view){

            finish();
            System.exit(0);
        }

    }

