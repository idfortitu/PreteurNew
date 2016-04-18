package com.example.idfortitu.preteurnew;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by idFortitu on 10/04/2016.
 */
public class DBManager extends SQLiteOpenHelper {

    String sqltableL="create table Objet(id INTEGER PRIMARY KEY AUTOINCREMENT, Titre TEXT, Commentaire TEXT, Pret INT ,Personne TEXT, Image TEXT, Nameimage TEXT)";
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqltableL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
