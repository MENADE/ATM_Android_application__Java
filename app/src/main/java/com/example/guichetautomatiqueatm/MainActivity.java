package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

     EditText txtUser;
     EditText txtPass;
    int cpt = 0;


    public  static Guichet guichet = new Guichet();
    public static  Client clientModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txtUser = findViewById(R.id.inputUser);
        txtPass = findViewById(R.id.inputPass);

        addClients ();

        }

   //Méthode d'instanciation des objets classe Cheque, Epargne, Client,
    // et remplissage des Arraylists correspondants : clients, comptesCheque, comptesEpargne.

        public void addClients ()
        {
            guichet.clients.add(new Client ("Papineau", "Julie", "pap", "P111"));
            guichet.clients.add(new Client ("Viger", "Jacques", "vig", "V222"));
            guichet.clients.add(new Client ("Desenne", "Catherine ", "des", "D333"));


            Cheque cheque1 = new Cheque ("P111",  "C111P",  1000.85);
            guichet.comptesCheque.add(cheque1);

            Cheque cheque2 = new Cheque("V222",  "C222P",  7000.50);
            guichet.comptesCheque.add(cheque2 );

            Cheque cheque3 = new Cheque("D333",  "C111D",  9000.20);
            guichet.comptesCheque.add(cheque3 );


                Epargne epargne1 =new Epargne("P111", "E111P", 3000.95);
            guichet.comptesEpargne.add(epargne1);

                  Epargne epargne2 =new Epargne("V222", "E111V", 4000.50);
            guichet.comptesEpargne.add(epargne2);

            Epargne epargne3 =new Epargne("D333", "E111D", 5000.50);
            guichet.comptesEpargne.add(epargne3);



        }

    public  void sign_Click(View view) {

        if (txtUser.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Tous les champs requis!", Toast.LENGTH_LONG).show();
        } else {

            if (txtUser.getText().toString().equals("admin") && (txtPass.getText().toString().equals("D001"))) {
                Intent eran_Adminstateur = new Intent(this, Ecran_Administrateur.class);
                startActivity(eran_Adminstateur);
            } else
                {
                for (int i = 0; i < guichet.clients.size()  ; i++)
                {

                    // Utilisation de la méthode ValiderUtilisateur pour valider l'identification

                    if (guichet.ValiderUtilisateur(txtUser.getText().toString(),txtPass.getText().toString()))
                    {

                      if (txtPass.getText().toString().equals(guichet.clients.get(i).getNumeroNIP())  )
                      {

                          clientModel= guichet.clients.get(i);

                          Intent ecran_Principal = new Intent(MainActivity.this, Ecran_Principal_Guichet.class);

                          //Transfert de l'ojet type Client <clientModel> vers l'activité <Ecran_Principal_Guichet>
                          // afin d'afficher son nom, son prenom ainsi de recupèrer son NIP
                          ecran_Principal.putExtra(Ecran_Principal_Guichet.EXTRA_DATA,  clientModel);

                          startActivity(ecran_Principal);
                      }

                    }

                }

                // Après la troisième essaie de Login -in le toast suivant va s'afficher !!
                if (cpt == 2) {
                    Toast.makeText(this, "Echec de sign in, veuillez SVP, essayer de réutiliser le guichet automatique plus tard !", Toast.LENGTH_LONG).show();
                } else
                    cpt++;

                }

        }
    }
}