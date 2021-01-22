package com.example.guichetautomatiqueatm;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Guichet implements Serializable  {

// création de listes pour les comptes Cheque, comptes Epargne, et Clients
    public  ArrayList<Cheque> comptesCheque = new ArrayList<>();
    public  ArrayList<Epargne> comptesEpargne = new ArrayList<>();
    public  ArrayList<Client> clients = new ArrayList<>();


    double nouveauSoldCheque =0;
    double nouveauSoldEpargne =0;

// Méthode pour vérifier la validation de login
    public  boolean ValiderUtilisateur(String username, String numeroNIP)
    {
            for (int i = 0; i < clients.size(); i++)
            {
                if (username.equals(clients.get(i).getUsername()) && (numeroNIP.equals(clients.get(i).getNumeroNIP())))
                return true;
            }

        return false;
    }

    //Méthode qui parcours la liste des comptes Cheque pour effectuer un retrait

   public double RetraitCheque(String numeroNIP, double montant)

   {

       for (int i = 0; i < MainActivity.guichet.comptesCheque.size(); i++)
       {

           if (MainActivity.guichet.comptesCheque.get(i).getNumeroNIP().equals(numeroNIP))


               nouveauSoldCheque = MainActivity.guichet.comptesCheque.get(i).retrait(montant);

       }
       return nouveauSoldCheque;
   }


    //Méthode qui parcours la liste des comptes Cheque pour effectuer un dépôt

    public double DepotCheque(String numeroNIP, double montant)
    {

        for (int i = 0; i < MainActivity.guichet.comptesCheque.size(); i++)
        {

            if (MainActivity.guichet.comptesCheque.get(i).getNumeroNIP().equals(numeroNIP))

                nouveauSoldCheque = MainActivity.guichet.comptesCheque.get(i).depot(montant);

          }
        return nouveauSoldCheque;
    }


    //Méthode qui parcours la liste des comptes Epargne pour effectuer un retrait

    public double RetraitEpargne(String numeroNIP, double montant)
    {

        for (int i = 0; i < MainActivity.guichet.comptesEpargne.size(); i++)
        {

            if (MainActivity.guichet.comptesEpargne.get(i).getNumeroNIP().equals(numeroNIP))

                nouveauSoldEpargne = MainActivity.guichet.comptesEpargne.get(i).retrait(montant);

        }
        return nouveauSoldEpargne;
    }


    //Méthode qui parcours la liste des comptes Epargne pour effectuer un dépôt

    public double DepotEpargne(String numeroNIP, double montant)

    {

        for (int i = 0; i < MainActivity.guichet.comptesEpargne.size(); i++)
        {

            if (MainActivity.guichet.comptesEpargne.get(i).getNumeroNIP().equals(numeroNIP))

                nouveauSoldEpargne = MainActivity.guichet.comptesEpargne.get(i).depot(montant);

        }
        return nouveauSoldEpargne;
    }


    //Méthode de virement vers le compte Epargne en débitant le compte Cheque

    public void VirementEpargne (String numeroNIP, double montant)
    {
        for (int i = 0; i < MainActivity.guichet.clients.size(); i++)
        {

            if (MainActivity.guichet.clients.get(i).getNumeroNIP().equals(numeroNIP)&& montant<100000)
            {
                RetraitCheque(numeroNIP, montant);
                DepotEpargne( numeroNIP,  montant);
            }
        }
    }

    //Méthode de virement vers le compte Cheque en débitant  le compte Epargne

    public void VirementCheque (String numeroNIP, double montant)
    {
        for (int i = 0; i < MainActivity.guichet.clients.size(); i++)
        {

            if (MainActivity.guichet.clients.get(i).getNumeroNIP().equals(numeroNIP) && montant<100000)
            {
                DepotCheque(numeroNIP, montant);
                RetraitEpargne( numeroNIP,  montant);
            }
        }
    }


    //Méthode de paiement des intérêts
    
    public double PaimentClientsInteret()

    {
        for (int i = 0; i < MainActivity.guichet.comptesEpargne.size(); i++)
        {
            if (MainActivity.guichet.comptesEpargne.get(i).getSoldeCompte()>0)

                nouveauSoldEpargne = MainActivity.guichet.comptesEpargne.get(i).paiementInteret();

        }
        return nouveauSoldEpargne;

    }

    public String afficherListClient()
    {
          ArrayList<Client> clients = new ArrayList<Client>();

        String infos = "";

        for (int i = 0 ; i< clients.size();i++)
        {
            infos += clients.get(i).toString();//  infos = infos + mesArtistes.get(i).toString();
        }
        return infos;
    }




}
