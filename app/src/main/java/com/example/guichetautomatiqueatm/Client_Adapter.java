package com.example.guichetautomatiqueatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class Client_Adapter extends ArrayAdapter<Client> {

    private ArrayList<Client> clients ;
    private  Context context;
    private int viewRes;
    private Resources res;

    public Client_Adapter(Context context, int textViewResourceId, ArrayList<Client> listClient) {

        super(context, textViewResourceId, listClient);
        this.clients = listClient;
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
        final Client currentClient = clients.get(position);
        if (currentClient != null) {
            final TextView txtNomClient = (TextView) view.findViewById(R.id.txtNomClient);
            final TextView txtPrenomClient = (TextView) view.findViewById(R.id.txtPrenomClient);

            String nom1 = res.getString(R.string.cleNomClient) +" " +currentClient.getNom();
            txtNomClient.setText(nom1);

            String prenom1 =res.getString(R.string.clePrenomClient) +" " +currentClient.getPrenom();
            txtPrenomClient.setText(prenom1);

        }
        return view;
    }
    @Override
    public int getCount() {
        return clients.size();
    }

}
