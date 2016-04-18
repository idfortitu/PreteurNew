package com.example.idfortitu.preteurnew;



import android.content.Intent;
import android.graphics.Bitmap;
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

public class ActivityAdd extends AppCompatActivity {
    private Button addpret;
    private Button cancel;
    LivreManager lm;
    EditText titre,auteur,nom;
    private Button addphoto;
    ImageView imgv;
    String img;
    TextView nomphoto;
    private String FichierPhoto = "";
    private static final int CAM_REQUEST = 1313;
    String Stockage;
    String filename = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        lm=new LivreManager(this);
        cancel = (Button)findViewById(R.id.bcancel);
        addpret = (Button)findViewById(R.id.baddpret);
        auteur= (EditText) findViewById(R.id.tauteur);
        titre= (EditText) findViewById(R.id.tlivre);
        nomphoto= (TextView) findViewById(R.id.tphoto);
        addphoto = (Button)findViewById(R.id.bphoto);
        imgv = (ImageView)findViewById(R.id.imageView);
        Bundle b = getIntent().getExtras();
        Stockage = b.getString("Stockage");


        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraintent, CAM_REQUEST);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        addpret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.open();
                Livre l = new Livre();
                String Titre=titre.getText().toString();
                String Auteur=auteur.getText().toString();
                l.setAuteur(Auteur);
                l.setTitre(Titre);
                l.setPreter(0);
                l.setimage(filename);
                l.setnimage(FichierPhoto);
                long lg = lm.AddLivre(l);
                lm.close();
                Intent intent = new Intent(ActivityAdd.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (auteur.getText().toString() == "") return;

        if(requestCode == CAM_REQUEST)
        {
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail((Bitmap) data.getExtras().get("data"), 96,96);
            imgv.setImageBitmap(thumbnail);

            FichierPhoto = auteur.getText().toString() + ".png";
            nomphoto.setText(FichierPhoto);
            filename = Environment.getExternalStorageDirectory() + "/" + Stockage + "/" + FichierPhoto;
            try
            {
                FileOutputStream out = new FileOutputStream(filename);
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
            catch (Exception e)
            {
                Log.e("ERROR:" + e.toString(), "impossible de sauver");
            }
            //nomphoto.setText(data.getExtras().get("data").toString());
        }
    }

}
