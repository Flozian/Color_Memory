package com.example.color_memory;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b_Facile = findViewById(R.id.buttonFacile);
        final Button b_Difficile = findViewById(R.id.buttonDifficile);
        final Button b_Expert = findViewById(R.id.buttonExpert);
        final Button b_Chrono = findViewById(R.id.buttonChrono);

        b_Facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancement_jeu = new Intent(MainActivity.this, Four_Buttons.class);
                lancement_jeu.putExtra("value",1);
                startActivity(lancement_jeu);
            }
        });

        b_Difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancement_jeu = new Intent(MainActivity.this, Four_Buttons.class);
                lancement_jeu.putExtra("value",2);
                startActivity(lancement_jeu);
            }
        });

        b_Expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancement_jeu = new Intent(MainActivity.this, Four_Buttons.class);
                lancement_jeu.putExtra("value",3);
                startActivity(lancement_jeu);
            }
        });

        b_Chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lancement_jeu = new Intent(MainActivity.this, Four_Buttons.class);
                lancement_jeu.putExtra("value",4);
                startActivity(lancement_jeu);
            }
        });
    }
}
