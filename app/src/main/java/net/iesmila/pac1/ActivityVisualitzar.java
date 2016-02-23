package net.iesmila.pac1;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.iesmila.pac1.model.Alignment;
import net.iesmila.pac1.model.Personatge;

/**
 * Created by Marc on 18/02/2016.
 */
public class ActivityVisualitzar extends Activity {

    private Personatge PersonatgeActual;

    LinearLayout mLlyVisualitzar;
    LinearLayout mLlyImatgeFons;
    LinearLayout mLlyStrength;
    LinearLayout mLlyDexterity;
    LinearLayout mLlyConstitution;
    LinearLayout mLlyIntelligence;
    LinearLayout mLlyWisdom;
    LinearLayout mLlyCharisma;

    ImageView mImvAlignment;
    ImageView mImvImatge;

    TextView mTxvNom;
    TextView mTxvSexe;
    TextView mTxvRasa;
    TextView mTxvOfici;
    TextView mTxvDescripcio;
    TextView mTxvNivell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_2);
        PersonatgeActual = (Personatge) getIntent().getSerializableExtra("Personatge");

        mLlyVisualitzar = (LinearLayout) findViewById(R.id.llyVisualitzacio);
        mLlyImatgeFons = (LinearLayout) findViewById(R.id.llyImatgeFons);
        mLlyStrength = (LinearLayout) findViewById(R.id.llyStrength);
        mLlyDexterity = (LinearLayout) findViewById(R.id.llyDexterity);
        mLlyConstitution = (LinearLayout) findViewById(R.id.llyConstitution);
        mLlyIntelligence = (LinearLayout) findViewById(R.id.llyIntelligence);
        mLlyWisdom = (LinearLayout) findViewById(R.id.llyWisdom);
        mLlyCharisma = (LinearLayout) findViewById(R.id.llyCharisma);

        mImvAlignment = (ImageView) findViewById(R.id.imvAlignment);
        mImvImatge = (ImageView) findViewById(R.id.imvImatge);

        mTxvNom = (TextView) findViewById(R.id.txvNomPersonatge);
        mTxvSexe = (TextView) findViewById(R.id.txvSexePersonatge);
        mTxvRasa = (TextView) findViewById(R.id.txvRasaPersonatge);
        mTxvOfici = (TextView) findViewById(R.id.txvOficiPersonatge);
        mTxvDescripcio = (TextView) findViewById(R.id.txvDescripcioPersonatge);
        mTxvNivell = (TextView) findViewById(R.id.txvNivell);

        mTxvDescripcio.setMovementMethod(new ScrollingMovementMethod());

        mLlyVisualitzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        carregarAbilitats(PersonatgeActual.getStrength(), mLlyStrength);
        carregarAbilitats(PersonatgeActual.getDexterity(), mLlyDexterity);
        carregarAbilitats(PersonatgeActual.getConstitution(), mLlyConstitution);
        carregarAbilitats(PersonatgeActual.getIntelligence(), mLlyIntelligence);
        carregarAbilitats(PersonatgeActual.getWisdom(), mLlyWisdom);
        carregarAbilitats(PersonatgeActual.getCharisma(), mLlyCharisma);


        CarregarImatgeAlignment();

        mTxvNom.setText(PersonatgeActual.getNom());
        mTxvSexe.setText(PersonatgeActual.getSexe().toString());
        mTxvRasa.setText(PersonatgeActual.getRasa().getRasa().toString());
        mTxvOfici.setText(PersonatgeActual.getOfici().getOfici());
        //mTxvDescripcio.setText(PersonatgeActual.getDescription());

        mImvImatge.setImageResource(PersonatgeActual.getImage());
        mTxvNivell.setText("Nivell: " + calcularNivell());
    }

    private void CarregarImatgeAlignment() {

        if (PersonatgeActual.getAlignement() == Alignment.ALLIANCE) {
            mImvAlignment.setImageResource(R.drawable.alliance);
            mLlyImatgeFons.setBackgroundResource(R.drawable.fons_stormwind);
        } else if (PersonatgeActual.getAlignement() == Alignment.HORDE) {
            mImvAlignment.setImageResource(R.drawable.horde);
            mLlyImatgeFons.setBackgroundResource(R.drawable.fons_orgrimar);
        }
    }

    private void carregarAbilitats(int NivellAbilitat, LinearLayout l) {

        int Nivell = NivellAbilitat / 5;
        int color;

        if (Nivell == 0 || Nivell < 6) color = getResources().getColor(R.color.estat1);
        else if (Nivell == 6 || Nivell < 11) color = getResources().getColor(R.color.estat2);
        else if (Nivell == 11 || Nivell < 16) color = getResources().getColor(R.color.estat3);
        else color = getResources().getColor(R.color.estat4);

        if (Nivell == 0) Nivell++;

        for (int i = 0; i < Nivell; i++) {
            ImageView im = new ImageView(ActivityVisualitzar.this);
            im.setImageResource(R.drawable.quadrat_estat);
            im.getDrawable().setColorFilter(color, PorterDuff.Mode.ADD);
            //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(2, 0, 0, 0);
            l.addView(im, lp);
        }
    }

    private int calcularNivell() {
        return (PersonatgeActual.getStrength() + PersonatgeActual.getDexterity() + PersonatgeActual.getConstitution() + PersonatgeActual.getIntelligence() + PersonatgeActual.getWisdom() + PersonatgeActual.getCharisma()) / 6;
    }

}
