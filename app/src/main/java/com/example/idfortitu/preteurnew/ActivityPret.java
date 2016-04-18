package com.example.idfortitu.preteurnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityPret extends AppCompatActivity {
    RadioButton radiobuttonpret;
    RadioButton radiobuttonrev;
    Button bok;
    LivreManager lm;
    String[] list;
    EditText nom;
    TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pret);
        radiobuttonpret = (RadioButton) findViewById(R.id.radioButton);
        radiobuttonrev = (RadioButton) findViewById(R.id.radioButton2);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        lm=new LivreManager(this);
        list = getIntent().getStringArrayExtra("com.myapp.liste");
        bok = (Button)findViewById(R.id.bok);
        nom = (EditText) findViewById(R.id.tnompersonne);
        tv = (TextView) findViewById(R.id.textviewnom);
        if (Integer.parseInt(list[3]) == 0)
        {
            radiobuttonrev.setChecked(true);
            nom.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        }
        else
        {
            radiobuttonpret.setChecked(true);
            nom.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            nom.setText(list[4]);
        }


        radiobuttonpret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiobuttonrev.setChecked(false);
                nom.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
            }
        });

        radiobuttonrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radiobuttonpret.setChecked(false);
                nom.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
            }
        });

        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.open();
                if (((RadioButton) radiobuttonrev).isChecked())
                {
                    lm.SupprimePret(Integer.parseInt(list[0]));
                    lm.close();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
                if (((RadioButton) radiobuttonpret).isChecked())
                {
                    lm.ActivePret(Integer.parseInt(list[0]), nom.getText().toString());
                    lm.close();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
