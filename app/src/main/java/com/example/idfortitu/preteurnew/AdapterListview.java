package com.example.idfortitu.preteurnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AdapterListview extends ArrayAdapter<String> {

    String[] liste;
    String[] listeinfo;
    LivreManager lm;
    TextView titre, nom;
    Bitmap bm;
    public AdapterListview(Context context, String[] liste) {
        super(context, R.layout.activity_customrow, liste);
        this.liste = liste;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflate = LayoutInflater.from(getContext());
        View customView = buckysInflate.inflate(R.layout.activity_customrow, parent, false);
        String single = getItem(position);
        titre = (TextView) customView.findViewById(R.id.textviewtitre);
        nom = (TextView) customView.findViewById(R.id.textnom);
        ImageView image = (ImageView) customView.findViewById(R.id.image);
        LivreManager lm = new LivreManager(getContext());
        lm.open();
        listeinfo = lm.AllLivre(position);
        titre.setText(single);
        String item = liste[position];


        image.setImageResource(R.mipmap.ic_launcher);

        try {
            bm = Bitmap.createBitmap(BitmapFactory.decodeFile(listeinfo[5]));
            image.setImageBitmap(bm);
        }catch (Exception e)
        {

        }



        if (Integer.parseInt(listeinfo[3]) == 0)
        {
            customView.setBackgroundColor(Color.GREEN);
            titre.setVisibility(View.GONE);
            nom.setText(single);
        }
        else
        {
            customView.setBackgroundColor(Color.RED);
            nom.setText(listeinfo[4]);
        }
        lm.close();
        return customView;
    }
}
