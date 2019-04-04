package com.example.color_memory;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.example.color_memory.MainActivity;

public class Four_Buttons extends AppCompatActivity {
    Button BTbleu;
    Button BTrouge;
    Button BTvert;
    Button BTjaune;
    Button BTdarkGreen;
    Button BTdarkYellow;
    Button BTlightGreen;
    Button BTblueLight;
    Button BTmarron;
    Button BTpurple;
    TextView NiveauActuel;
    TextView ScoreActuel;
    Animation animation;
    int currrentNbColors=1;
    List<Integer> SequenceAtaper = new ArrayList<>();
    List<Integer> SequenceTaper = new ArrayList<>();
    int temps_reponse=0;
    boolean drapeau_BT_bloque=false;

    private int PointDeVie,NbcouleurMax,Nbsequence,NbNiveau,NbNiveauMax;
    private double ScoreMultiplicateur;
    private Random _random;
    boolean CREATseq=true;
    double score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        final Button b_lancement = findViewById(R.id.btn_lancement);

        b_lancement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NbNiveau = 1;
                switchLvl();
            }
        });
        Bundle value = getIntent().getExtras();
        switchLvl();
        switchDifficulter(value);
        switchBoutons();



        animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        _random = new Random();

        SequenceAtaper.add(1);

    }


    void test_sequence()
    {
        if(SequenceAtaper.equals(SequenceTaper))
        {
            NbcouleurMax++;
            if(NbcouleurMax>=Nbsequence)
            {
                NbcouleurMax=1;
                NbNiveau++;
                CREATseq=true;
                score=+NbNiveau*ScoreMultiplicateur;
            }else
            {
                CreaSequence();
            }
            if(NbNiveau>NbNiveauMax)
            {
                Intent score = new Intent(Four_Buttons.this, Score.class);
                score.putExtra("resultat",1);
                startActivity(score);
                finish();

            }
            switchLvl();

        }
        else if(SequenceTaper.size()>=SequenceAtaper.size())
        {
            PointDeVie--;

            switch (PointDeVie) {
                case 1:
                    TextView textView = findViewById(R.id.textVie);
                    textView.setText("1");
                    break;
                case 0:
                    TextView textView2 = findViewById(R.id.textVie);
                    textView2.setText("0");
                    break;
            }
            Test_Vie();
            CreaSequence();


        }

    }

    public void Test_Vie()
    {
        if(PointDeVie<=0) {
            Intent score = new Intent(Four_Buttons.this, Score.class);
            score.putExtra("resultat",0);
            startActivity(score);
            finish();
        }
    }


    public void switchDifficulter(Bundle value){
        if(value!=null){
            int Valeur = (int)value.get("value");
            NbNiveau = 1;
            switch (Valeur) {
                case 1:
                    PointDeVie = 2;
                    ScoreMultiplicateur = 1;
                    NbcouleurMax=1;
                    Nbsequence=7;
                    NbNiveauMax=7;

                    break;
                case 2:
                    PointDeVie = 2;
                    ScoreMultiplicateur = 1.5;
                    NbcouleurMax=3;
                    Nbsequence=10;
                    NbNiveauMax=8;
                    break;
                case 3:
                    PointDeVie = 2;
                    ScoreMultiplicateur = 2;
                    NbcouleurMax=4;
                    Nbsequence=12;
                    NbNiveauMax=9;
                    break;
                case 4:
                    PointDeVie = 3;
                    ScoreMultiplicateur = 1.5;
                    Nbsequence=8;
                    NbNiveauMax=8;
                    temps_reponse=2*NbcouleurMax;
                    break;
                default:
                    break;
            }
        }
    }

    public void CreaSequence() {
        SequenceAtaper = new ArrayList<>();
        SequenceTaper = new ArrayList<>();
        currrentNbColors = 1;

        new Thread(new Runnable() {
            @Override
            public void run() {
                do{
                    setBoutonsindisponible();try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                    }
                }while (!drapeau_BT_bloque);
                setBoutonsDisponible();
                drapeau_BT_bloque=false;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NbcouleurMax; i++) {
                    int nombreAleatoire;
                    nombreAleatoire = 1 + (int) (Math.random() * (NbNiveau+3));
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                    }
                    switch (nombreAleatoire) {
                        case 1:
                            SequenceAtaper.add(nombreAleatoire);
                            BTbleu.startAnimation(animation);
                            break;
                        case 2:
                            SequenceAtaper.add(nombreAleatoire);
                            BTrouge.startAnimation(animation);
                            break;
                        case 3:
                            SequenceAtaper.add(nombreAleatoire);
                            BTvert.startAnimation(animation);
                            break;
                        case 4:
                            SequenceAtaper.add(nombreAleatoire);
                            BTjaune.startAnimation(animation);
                            break;
                        case 5:
                            SequenceAtaper.add(nombreAleatoire);
                            BTpurple.startAnimation(animation);
                            break;
                        case 6:
                            SequenceAtaper.add(nombreAleatoire);
                            BTblueLight.startAnimation(animation);
                            break;
                        case 7:
                            SequenceAtaper.add(nombreAleatoire);
                            BTdarkGreen.startAnimation(animation);
                            break;
                        case 8:
                            SequenceAtaper.add(nombreAleatoire);
                            BTdarkYellow.startAnimation(animation);
                            break;
                        case 9:
                            SequenceAtaper.add(nombreAleatoire);
                            BTlightGreen.startAnimation(animation);
                            break;
                        case 10:
                            SequenceAtaper.add(nombreAleatoire);
                            BTmarron.startAnimation(animation);
                            break;

                    }
                    if (i==NbcouleurMax-1)
                        drapeau_BT_bloque=true;
                    if(NbNiveau==4)
                    {
                        temps_reponse=2*NbcouleurMax;

                    }


                }
            }
        }).start();
    }

    public void setBoutonsindisponible(){
        BTbleu.setClickable(false);
        BTrouge.setClickable(false);
        BTvert.setClickable(false);
        BTjaune.setClickable(false);

        switch (NbNiveau){
            case 2:
                BTpurple.setClickable(false);
                break;
            case 3:
                BTblueLight.setClickable(false);
                BTpurple.setClickable(false);
                break;
            case 4:
                BTblueLight.setClickable(false);
                BTdarkGreen.setClickable(false);
                BTpurple.setClickable(false);
                break;
            case 5:
                BTblueLight.setClickable(false);
                BTdarkGreen.setClickable(false);
                BTdarkYellow.setClickable(false);
                BTpurple.setClickable(false);
                break;

            case 6:
                BTblueLight.setClickable(false);
                BTdarkGreen.setClickable(false);
                BTdarkYellow.setClickable(false);
                BTlightGreen.setClickable(false);
                BTpurple.setClickable(false);
                break;

            case 7:
                BTblueLight.setClickable(false);
                BTdarkGreen.setClickable(false);
                BTdarkYellow.setClickable(false);
                BTlightGreen.setClickable(false);
                BTmarron.setClickable(false);
                BTpurple.setClickable(false);
                break;

        }
    }

    public void setBoutonsDisponible(){
        BTbleu.setClickable(true);
        BTrouge.setClickable(true);
        BTvert.setClickable(true);
        BTjaune.setClickable(true);

        switch (NbNiveau){
            case 2:
                BTpurple.setClickable(true);
                break;
            case 3:
                BTblueLight.setClickable(true);
                BTpurple.setClickable(true);
                break;
            case 4:
                BTblueLight.setClickable(true);
                BTdarkGreen.setClickable(true);
                BTpurple.setClickable(true);
                break;
            case 5:
                BTblueLight.setClickable(true);
                BTdarkGreen.setClickable(true);
                BTdarkYellow.setClickable(true);
                BTpurple.setClickable(true);
                break;

            case 6:
                BTblueLight.setClickable(true);
                BTdarkGreen.setClickable(true);
                BTdarkYellow.setClickable(true);
                BTlightGreen.setClickable(true);
                BTpurple.setClickable(true);
                break;

            case 7:
                BTblueLight.setClickable(true);
                BTdarkGreen.setClickable(true);
                BTdarkYellow.setClickable(true);
                BTlightGreen.setClickable(true);
                BTmarron.setClickable(true);
                BTpurple.setClickable(true);
                break;

        }
    }

    public void switchLvl(){
        switch (NbNiveau) {
            case 1:

                findViewById(R.id.vue_FourButtons).setVisibility(View.VISIBLE);
                findViewById(R.id.btn_lancement).setVisibility(View.GONE);
                TextView textView = findViewById(R.id.textNiveauActuel);
                textView.setText("1");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 2:
                findViewById(R.id.vue_FourButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_FiveButtons).setVisibility(View.VISIBLE);
                TextView textView2 = findViewById(R.id.textNiveauActuel);
                textView2.setText("2");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 3:

                findViewById(R.id.vue_FiveButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_SixButtons).setVisibility(View.VISIBLE);
                TextView textView3 = findViewById(R.id.textNiveauActuel);
                textView3.setText("3");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 4:

                findViewById(R.id.vue_SixButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_SevenButtons).setVisibility(View.VISIBLE);
                TextView textView4 = findViewById(R.id.textNiveauActuel);
                textView4.setText("4");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 5:

                findViewById(R.id.vue_SevenButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_EightButtons).setVisibility(View.VISIBLE);
                TextView textView5 = findViewById(R.id.textNiveauActuel);
                textView5.setText("5");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 6:

                findViewById(R.id.vue_EightButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_NineButtons).setVisibility(View.VISIBLE);
                TextView textView6 = findViewById(R.id.textNiveauActuel);
                textView6.setText("6");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            case 7:

                findViewById(R.id.vue_NineButtons).setVisibility(View.GONE);
                findViewById(R.id.vue_TenButtons).setVisibility(View.VISIBLE);
                TextView textView7 = findViewById(R.id.textNiveauActuel);
                textView7.setText("7");
                switchBoutons();
                if(CREATseq)
                {
                    CreaSequence();
                    CREATseq=false;
                }
                break;
            default:
                break;
        }



    }


    public void switchBoutons(){
        switch (NbNiveau) {
            case 1:
                BTbleu = findViewById(R.id.buttonBleu);
                BTrouge = findViewById(R.id.buttonRouge);
                NiveauActuel = findViewById(R.id.textNiveauActuel);
                ScoreActuel = findViewById(R.id.textScoreActuel);
                BTvert = findViewById(R.id.buttonVert);
                BTjaune = findViewById(R.id.buttonJaune);
                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                break;
            case 2:
                BTbleu = findViewById(R.id.buttonBleu2);
                BTrouge = findViewById(R.id.buttonRouge2);
                BTvert = findViewById(R.id.buttonVert2);
                BTjaune = findViewById(R.id.buttonJaune2);
                BTpurple = findViewById(R.id.buttonPurple2);
                NiveauActuel = findViewById(R.id.textNiveauActuel2);
                ScoreActuel = findViewById(R.id.textScoreActuel2);
                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                break;
            case 3:
                BTbleu = findViewById(R.id.buttonBleu3);
                BTrouge = findViewById(R.id.buttonRouge3);
                BTvert = findViewById(R.id.buttonVert3);
                BTjaune = findViewById(R.id.buttonJaune3);
                BTpurple = findViewById(R.id.buttonPurple3);
                BTblueLight = findViewById(R.id.buttonCyan3);
                NiveauActuel = findViewById(R.id.textNiveauActuel3);
                ScoreActuel = findViewById(R.id.textScoreActuel3);

                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTblueLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(6);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });

                break;
            case 4:
                BTbleu = findViewById(R.id.buttonBleu4);
                BTrouge = findViewById(R.id.buttonRouge4);
                BTvert = findViewById(R.id.buttonVert4);
                BTjaune = findViewById(R.id.buttonJaune4);
                BTpurple = findViewById(R.id.buttonPurple4);
                BTblueLight = findViewById(R.id.buttonCyan4);
                BTdarkGreen = findViewById(R.id.buttonGreenDark4);
                NiveauActuel = findViewById(R.id.textNiveauActuel4);
                ScoreActuel = findViewById(R.id.textScoreActuel4);

                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTblueLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(6);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(7);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });

                break;
            case 5:
                BTbleu = findViewById(R.id.buttonBleu5);
                BTrouge = findViewById(R.id.buttonRouge5);
                BTvert = findViewById(R.id.buttonVert5);
                BTjaune = findViewById(R.id.buttonJaune5);
                BTpurple = findViewById(R.id.buttonPurple5);
                BTblueLight = findViewById(R.id.buttonCyan5);
                BTdarkGreen = findViewById(R.id.buttonGreenDark5);
                BTdarkYellow = findViewById(R.id.buttonYellowDark5);
                NiveauActuel = findViewById(R.id.textNiveauActuel5);
                ScoreActuel = findViewById(R.id.textScoreActuel5);

                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTblueLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(6);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(7);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkYellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(8);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });

                break;
            case 6:
                BTbleu = findViewById(R.id.buttonBleu6);
                BTrouge = findViewById(R.id.buttonRouge6);
                BTvert = findViewById(R.id.buttonVert6);
                BTjaune = findViewById(R.id.buttonJaune6);
                BTpurple = findViewById(R.id.buttonPurple6);
                BTblueLight = findViewById(R.id.buttonCyan6);
                BTdarkGreen = findViewById(R.id.buttonGreenDark6);
                BTdarkYellow = findViewById(R.id.buttonYellowDark6);
                BTlightGreen = findViewById(R.id.buttonGreenLight6);
                NiveauActuel = findViewById(R.id.textNiveauActuel6);
                ScoreActuel = findViewById(R.id.textScoreActuel6);

                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTblueLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(6);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(7);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkYellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(8);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTlightGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(9);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                break;
            case 7:
                BTbleu = findViewById(R.id.buttonBleu7);
                BTrouge = findViewById(R.id.buttonRouge7);
                BTvert = findViewById(R.id.buttonVert7);
                BTjaune = findViewById(R.id.buttonJaune7);
                BTpurple = findViewById(R.id.buttonPurple7);
                BTblueLight = findViewById(R.id.buttonCyan7);
                BTdarkGreen = findViewById(R.id.buttonGreenDark7);
                BTdarkYellow = findViewById(R.id.buttonYellowDark7);
                BTlightGreen = findViewById(R.id.buttonGreenLight7);
                BTmarron = findViewById(R.id.buttonMarron7);
                NiveauActuel = findViewById(R.id.textNiveauActuel7);
                ScoreActuel = findViewById(R.id.textScoreActuel7);

                BTbleu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(1);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTjaune.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(4);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();
                    }
                });
                BTrouge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(2);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTvert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(3);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTpurple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(5);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTblueLight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(6);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(7);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTdarkYellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(8);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTlightGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(9);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });
                BTmarron.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SequenceTaper.add(10);
                        currrentNbColors++;
                        test_sequence();
                        switchLvl();

                    }
                });

                break;
            default:
                break;
        }

    }
}
