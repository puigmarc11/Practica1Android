package net.iesmila.pac1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import net.iesmila.pac1.model.Personatge;

import java.util.ArrayList;
import java.util.List;

public class ActivityGestio extends ActionBarActivity {

    private ListView mLsvGestioPersonatges;
    private GestioAdapterPersonatges mAdapter;

    private ImageView mImvFiltre;
    private ImageView mImvNoFiltre;
    private ImageView mImvEliminar;
    private ImageView mImvEditar;
    private ImageView mImvNou;
    private ImageView mImvBackGestio;

    private EditText mEdtNomProfessio;

    private List<Personatge> mLlistaFiltrada = new ArrayList<>();

    Boolean filtre = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_gestio);

        mImvBackGestio = (ImageView) findViewById(R.id.imvBackGestio);
        mImvFiltre = (ImageView) findViewById(R.id.imvGestioFiltre);
        mImvNoFiltre = (ImageView) findViewById(R.id.imvGestioNoFiltre);
        mImvEliminar = (ImageView) findViewById(R.id.imvGestioEliminar);
        mImvEditar = (ImageView) findViewById(R.id.imvGestioEdicio);
        mImvNou = (ImageView) findViewById(R.id.imvNewGestio);

        mEdtNomProfessio = (EditText) findViewById(R.id.edtNomProfessioGestio);

        mImvBackGestio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mImvEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGestio.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mLlistaFiltrada = Personatge.getPersonatges();

        mLsvGestioPersonatges = (ListView) findViewById(R.id.lsvGestioPersonatges);
        mAdapter = new GestioAdapterPersonatges(this, mLlistaFiltrada);
        mLsvGestioPersonatges.setAdapter(mAdapter);
        mLsvGestioPersonatges.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        mImvEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personatge.getPersonatges().remove(mAdapter.getIndex());
                Log.i("XXX", String.valueOf(mAdapter.getIndex()));
                mAdapter.notifyDataSetChanged();
                if (Personatge.getPersonatges().size() == 0) habilitarBotons(false);
            }
        });

        View.OnClickListener filtreListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlistaFiltrada = new ArrayList<>();
                switch (view.getId()) {
                    case R.id.imvGestioFiltre:
                        filtre = true;
                        mAdapter.getFilter().filter(mEdtNomProfessio.getText());
                        break;
                    case R.id.imvGestioNoFiltre:
                        filtre = false;
                        mAdapter.getFilter().filter(null);
                        mEdtNomProfessio.setText("");
                        break;
                }
                Log.i("XXX", "LIST: ");
                habilitarBotonsFiltre(filtre);
                habilitarBotons(false);
                mLsvGestioPersonatges.clearChoices();
            }
        };

        mEdtNomProfessio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().equals("")) {
                    habilitarBotonsFiltre(false);
                } else {
                    habilitarBotonsFiltre(true);
                }
            }
        });

        mImvFiltre.setOnClickListener(filtreListener);
        mImvNoFiltre.setOnClickListener(filtreListener);

        //Deshabilitem els botons al inici
        habilitarBotons(false);
        habilitarBotonsFiltre(false);

    }

    public void habilitarBotons(Boolean b) {
        mImvEditar.setEnabled(b);
        mImvEliminar.setEnabled(b);
    }

    public void habilitarBotonsFiltre(Boolean b) {
        mImvNoFiltre.setEnabled(filtre);
        mImvFiltre.setEnabled(b);
    }

}
