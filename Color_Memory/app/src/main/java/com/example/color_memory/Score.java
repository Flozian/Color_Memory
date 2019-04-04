package com.example.color_memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        Bundle resultat = getIntent().getExtras();
        if(resultat!=null) {
            int Resultat = (int)resultat.get("resultat");
            switch (Resultat) {
                case 0:
                    TextView textView = findViewById(R.id.textResultat);
                    textView.setText(R.string.textResultatlose);

                    break;
                case 1:
                    TextView textView2 = findViewById(R.id.textResultat);
                    textView2.setText(R.string.textResultatWin);
                    break;

            }
        }
    }
}
