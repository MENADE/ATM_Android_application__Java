package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Liste_Comptes_Cheque extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste__comptes__cheque);




        Cheque_Adapter adapter = new Cheque_Adapter(this, R.layout.list_generique_layout, MainActivity.guichet.comptesCheque );

        final ListView list = (ListView) findViewById(R.id.txtCheque);

        list.setAdapter(adapter);
    }
}