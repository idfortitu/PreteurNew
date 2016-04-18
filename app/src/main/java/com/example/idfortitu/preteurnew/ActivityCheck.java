package com.example.idfortitu.preteurnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by idFortitu on 11/04/2016.
 */
public class ActivityCheck extends AppCompatActivity {
    EditText titre,auteur;
    private Button del;
    private Button cancel;
    private Button addphoto;
    ImageView imgv;
    private static final int CAM_REQUEST = 1313;
    LivreManager lm;
    Bitmap bm;
    TextView nomphoto;
    String t;
    String Stockage;
    String filename;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        String[] list = getIntent().getStringArrayExtra("com.myapp.liste");
        Stockage = b.getString("Stockage");
        nomphoto= (TextView) findViewById(R.id.nomphoto);
        cancel = (Button)findViewById(R.id.bcancel);
        del = (Button)findViewById(R.id.bdel);
        auteur= (EditText) findViewById(R.id.editText);
        lm=new LivreManager(this);
        t = list[0];
        auteur.setText(list[1], TextView.BufferType.EDITABLE);
        titre= (EditText) findViewById(R.id.editText2);
        titre.setText(list[2], TextView.BufferType.EDITABLE);
        addphoto = (Button)findViewById(R.id.bphoto);
        imgv = (ImageView)findViewById(R.id.imageView2);


        imgv.setImageResource(R.mipmap.ic_launcher);

        try {
            bm = Bitmap.createBitmap(BitmapFactory.decodeFile(list[5]));
            imgv.setImageBitmap(bm);
            nomphoto.setText(list[6]);
        }catch (Exception e)
        {

        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.open();
                lm.DelLivre(Integer.parseInt(t));
                lm.close();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }



}
