package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Liste_Comptes_Epargne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste__comptes__epargne);




        Epargne_Adapter adapter = new Epargne_Adapter(this, R.layout.list_generique_layout, MainActivity.guichet.comptesEpargne );

        final ListView list = (ListView) findViewById(R.id.txtEpargne);

        list.setAdapter(adapter);

    }
}