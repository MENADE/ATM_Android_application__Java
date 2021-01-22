package com.example.guichetautomatiqueatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Liste_Clients extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste__clients);

        Client_Adapter adapter = new Client_Adapter(this, R.layout.list_generique_layout,MainActivity.guichet.clients );

        final ListView list = (ListView) findViewById(R.id.txtlistClient);

        list.setAdapter(adapter);

    }



}