package net.iesmila.pac1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityCarrega extends ActionBarActivity {

    private SpashAsyncTask mSAT;
    private TextView mTxvSpalsh;

    private FrameLayout mfrlSpashScreen;
    private LinearLayout mllySplashScreen;

    private ImageView mImvVisualitzarCartes;
    private ImageView mImvComprarCartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        mTxvSpalsh = (TextView) findViewById(R.id.txvSplash);
        mfrlSpashScreen = (FrameLayout) findViewById(R.id.frlSpashScreen);
        mllySplashScreen = (LinearLayout) findViewById(R.id.mllySplashScreen);

        mImvVisualitzarCartes = (ImageView) findViewById(R.id.imvVisualitzarCartes);
        mImvComprarCartes = (ImageView) findViewById(R.id.imvComprarCartes);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.imvVisualitzarCartes:
                        Intent intent = new Intent(ActivityCarrega.this, ActivityGestio.class);
                        startActivity(intent);
                        break;
                    case R.id.imvComprarCartes:
                        intent = new Intent(ActivityCarrega.this, ActivityBotiga.class);
                        startActivity(intent);
                        break;
                }

            }
        };

        mImvVisualitzarCartes.setOnClickListener(click);
        mImvComprarCartes.setOnClickListener(click);

        mfrlSpashScreen.setVisibility(View.VISIBLE);
        mllySplashScreen.setVisibility(View.INVISIBLE);

        mSAT = new SpashAsyncTask(this);
        mSAT.execute();

    }

    ////SPASH SCREEN

    public void mostrarProgressDescarrega(String name) {
        mTxvSpalsh.setText(name);
    }

    public void mostrarErrorDescarrega() {
        Log.e("PAC2", "Error en carregar proces spashScreen");
    }

    public void fiDescarrega(String name) {
        mfrlSpashScreen.setVisibility(View.INVISIBLE);
        mllySplashScreen.setVisibility(View.VISIBLE);
    }

    ///////////////
}
