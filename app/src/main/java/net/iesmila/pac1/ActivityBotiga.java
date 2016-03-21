package net.iesmila.pac1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import net.iesmila.pac1.model.Personatge;

public class ActivityBotiga extends ActionBarActivity {

    private ImageView mBtnBackBotiga;
    private ListView mLsvBotiga;
    private BotigaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_botiga);

        mBtnBackBotiga = (ImageView) findViewById(R.id.btnBackBotiga);
        mBtnBackBotiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLsvBotiga = (ListView) findViewById(R.id.lsvBotiga);

        mAdapter = new BotigaAdapter(this, Personatge.getPersonatges());
        mLsvBotiga.setAdapter(mAdapter);
        mLsvBotiga.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }
}
