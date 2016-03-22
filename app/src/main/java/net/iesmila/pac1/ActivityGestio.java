package net.iesmila.pac1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;


import net.iesmila.pac1.model.Personatge;

import java.util.ArrayList;
import java.util.List;

public class ActivityGestio extends ActionBarActivity {

    private ListView mLsvGestioPersonatges;
    private GestioAdapterPersonatges mAdapter;

    private RecyclerView mLsvGestioFiltre;
    private RecyclerView.Adapter mAdapterFiltre;
    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView mImvFiltre;
    private ImageView mImvNoFiltre;
    private ImageView mImvEliminar;
    private ImageView mImvEditar;
    private ImageView mImvNou;
    private ImageView mImvBackGestio;

    private EditText mEdtNomProfessio;

    private List<Personatge> mLlistaFiltrada;
    private List<String> mLlistaHoritzontal;

    private final static int ID_INTENT = 1;
    public final static String ENVIAR_DADES = "enviar";
    public final static String REBRE_DADES = "rebre";

    Boolean filtre = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("CC", "Fase1");

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_gestio);

        Log.i("CC", "Fase2");

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
                Log.i("Personatge", "Index: Enviar " + String.valueOf(mAdapter.posicioRealLlistaOriginal(mAdapter.getItem(mAdapter.getIndex()))));
                Intent intent = new Intent(ActivityGestio.this, MainActivity.class);
                intent.putExtra(ENVIAR_DADES, mAdapter.posicioRealLlistaOriginal(mAdapter.getItem(mAdapter.getIndex())));
                startActivityForResult(intent, 1);
            }
        });

        mImvNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Personatge", "Index: Enviar Nou Personatge");
                Intent intent = new Intent(ActivityGestio.this, MainActivity.class);
                //Enviem un -1 perque sapiga que ha de ser un personatge nou. És un index que mai podrà existir.
                intent.putExtra(ENVIAR_DADES, -1);
                startActivityForResult(intent, 1);
            }
        });

        mLlistaFiltrada = Personatge.getPersonatges();

        mLsvGestioPersonatges = (ListView) findViewById(R.id.lsvGestioPersonatges);
        mAdapter = new GestioAdapterPersonatges(this, mLlistaFiltrada);
        mLsvGestioPersonatges.setAdapter(mAdapter);
        mLsvGestioPersonatges.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //-----------------------------------------------//
        mLlistaHoritzontal = new ArrayList<>();
        mLlistaHoritzontal.add("DDDD");
        mLlistaHoritzontal.add("DDDD");
        mLlistaHoritzontal.add("DDDD");
        mLlistaHoritzontal.add("DDDD");
        mLlistaHoritzontal.add("DDDD");

        List<Integer> mLlistaSiluetes = new ArrayList<>();
        mLlistaSiluetes.add(R.drawable.s1);
        mLlistaSiluetes.add(R.drawable.s2);
        mLlistaSiluetes.add(R.drawable.s3);
        mLlistaSiluetes.add(R.drawable.s4);
        mLlistaSiluetes.add(R.drawable.s1);
        mLlistaSiluetes.add(R.drawable.s2);
        mLlistaSiluetes.add(R.drawable.s3);
        mLlistaSiluetes.add(R.drawable.s4);

        mLsvGestioFiltre = (RecyclerView) findViewById(R.id.lsvGestioFiltre);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.lsvGestioFiltre);
        myList.setLayoutManager(layoutManager);

        mAdapterFiltre = new GestioAdapterFiltre(this, mLlistaSiluetes);
        mLsvGestioFiltre.setAdapter(mAdapterFiltre);
        //-----------------------------------------------//

        mImvEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = mAdapter.getIndex();
                mAdapter.removeAt(mAdapter.getIndex());
                mAdapter.notifyDataSetChanged();
                if (i >= mAdapter.getCount()) {
                    mAdapter.checkItem(mLsvGestioPersonatges, i - 1);
                    mLsvGestioPersonatges.setSelection(i - 1);
                }
            }
        });

        View.OnClickListener filtreListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.imvGestioFiltre:
                        filtre = true;
                        mAdapter.filtrarLlista(mEdtNomProfessio.getText().toString());
                        break;
                    case R.id.imvGestioNoFiltre:
                        filtre = false;
                        mAdapter.getFilter().filter(null);
                        mEdtNomProfessio.setText("");
                        break;
                }
                habilitarBotonsFiltre(filtre);
                habilitarBotons(false);
                mLsvGestioPersonatges.clearChoices();
            }
        };

        mImvFiltre.setOnClickListener(filtreListener);
        mImvNoFiltre.setOnClickListener(filtreListener);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (ID_INTENT): {
                if (resultCode == Activity.RESULT_OK) {
                    int tabIndex = data.getIntExtra(REBRE_DADES, -1);
                    Log.i("CCC", "Index: retorn " + String.valueOf(tabIndex));
                    Log.i("CCC", "MAX:  " + String.valueOf(mAdapter.getCount()));
                    mAdapter.notifyDataSetChanged();
                    if (tabIndex != mAdapter.getIndex() && tabIndex != -1) {
                        mAdapter.getFilter().filter(null);
                        mEdtNomProfessio.setText("");
                        mAdapter.checkItem(mLsvGestioPersonatges, tabIndex);
                        mLsvGestioPersonatges.setSelection(tabIndex);
                        habilitarBotonsFiltre(true);
                        habilitarBotons(true);
                    }
                }
                break;
            }
        }
    }
}
