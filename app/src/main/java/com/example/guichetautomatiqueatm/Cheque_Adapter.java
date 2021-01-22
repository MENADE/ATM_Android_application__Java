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

public class Cheque_Adapter extends ArrayAdapter<Cheque> {

public  ArrayList<Cheque> comptesCheque ;

private  Context context;
private int viewRes;
private Resources res;

public Cheque_Adapter(Context context, int textViewResourceId, ArrayList<Cheque> comptesCheque) {

        super(context, textViewResourceId, comptesCheque);
        this.comptesCheque = comptesCheque;
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
final Cheque currentCheque = comptesCheque.get(position);
        if (currentCheque != null) {
final TextView txtNomClient = (TextView) view.findViewById(R.id.txtNomClient);
final TextView txtPrenomClient = (TextView) view.findViewById(R.id.txtPrenomClient);

                        ImageView imageChe = view.findViewById(R.id.icon);

                        imageChe.setImageResource(R.drawable.cheque_mage);

        String numeroCheque = res.getString(R.string.cleNumCpt) +" " +currentCheque.getNumeroCompte();
        txtNomClient.setText(numeroCheque);

        String soldeCheque =res.getString(R.string.cleSoldCheque) +" " +currentCheque.getSoldeCompte();
        txtPrenomClient.setText(soldeCheque);

        }
        return view;
        }
@Override
public int getCount() {
        return comptesCheque.size();
        }

        }
