package com.example.idfortitu.preteurnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by idFortitu on 10/04/2016.
 */
public class LivreManager {
    DBManager dbm;
    SQLiteDatabase db;
    public LivreManager(Context ctx)
    {
        dbm=new DBManager(ctx, "mybase", null, 1);
    }
    public void open()
    {
        db = dbm.getWritableDatabase();
    }
    public void close()
    {
        db.close();
    }

    public long ActivePret (int id, String p) {
        ContentValues values = new ContentValues();
        values.put("Pret", "1");
        values.put("Personne" ,p);
        return db.update("Objet", values, "id" + " = " +id, null);
    }
    public long SupprimePret (int id) {
        ContentValues values = new ContentValues();
        values.put("Pret", "0");
        values.put("Personne", "");
        return db.update("Objet", values, "id" + " = " +id, null);
    }


    public long AddLivre (Livre l)
    {
        ContentValues vals= new ContentValues();
        vals.put("Titre", l.getTitre());
        vals.put("Commentaire", l.getAuteur());
        vals.put("Pret", 0);
        vals.put("Personne", "");
        vals.put("Image",l.getImage());
        vals.put("Nameimage",l.getnImage());
        return db.insert("Objet", null, vals);
    }

    public long DelLivre(int id)
    {
        return db.delete("Objet", "id" + " = " +id, null);
    }

    public String[] Allog()
    {
        String [] all;
        Cursor c = db.query("Objet", new String[] {"Titre" , "Commentaire", "Pret"}, null, null, null, null, null);
        int dim = c.getCount();
        all=new String[dim];
        int i = 0;
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            all[i]=c.getString(0);
            i++;
            c.moveToNext();
        }
        c.close();
        return all;
    }



    public String[] AllLivre(int indice)
    {
        String [] all;
        Cursor c = db.query("Objet", new String[] {"id, Titre", "Commentaire", "Pret", "Personne", "Image", "Nameimage"}, null, null, null, null, null);
        int dim = c.getCount();
        all=new String[7];
        c.moveToFirst();
        c.moveToPosition(indice);
        all[0]=c.getString(0);
        all[1]=c.getString(1);
        all[2]=c.getString(2);
        all[3]=c.getString(3);
        all[4]=c.getString(4);
        all[5]=c.getString(5);
        all[6]=c.getString(6);
        c.close();
        return all;
    }

}
