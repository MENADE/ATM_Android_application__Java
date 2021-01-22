package com.example.guichetautomatiqueatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class Epargne_Adapter extends ArrayAdapter<Epargne> {

    public  ArrayList<Epargne> comptesEpargne ;

    private  Context context;
    private int viewRes;
    private Resources res;

    public Epargne_Adapter(Context context, int textViewResourceId, ArrayList<Epargne> comptesEpargne) {

        super(context, textViewResourceId, comptesEpargne);
        this.comptesEpargne = comptesEpargne;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Epargne currentEpargne = comptesEpargne.get(position);
        if (currentEpargne != null) {
            final TextView numCptEpargne = (TextView) view.findViewById(R.id.txtNomClient);
            final TextView soldEpargne = (TextView) view.findViewById(R.id.txtPrenomClient);

            ImageView imageChe = view.findViewById(R.id.icon);

            imageChe.setImageResource(R.drawable.epargne);

            String numeroEpargne = res.getString(R.string.cleNumCpt) +"" +currentEpargne.getNumeroCompte();
            numCptEpargne.setText(numeroEpargne);

            String soldeEpargne =res.getString(R.string.cleSoldEpargne) +"" +currentEpargne.getSoldeCompte();
            soldEpargne.setText(soldeEpargne);

        }
        return view;
    }
    @Override
    public int getCount() {
        return comptesEpargne.size();
    }

}