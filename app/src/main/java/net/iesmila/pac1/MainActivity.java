package net.iesmila.pac1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import net.iesmila.pac1.model.Alignment;
import net.iesmila.pac1.model.Imatge;
import net.iesmila.pac1.model.Ofici;
import net.iesmila.pac1.model.Personatge;
import net.iesmila.pac1.model.Rasa;
import net.iesmila.pac1.model.Sexe;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends ActionBarActivity {

    private RadioGroup mRgTipus;
    private RadioButton mRdbAlliance;
    private RadioButton mRdbHorde;
    private RadioButton mRdbNeutral;

    private RadioGroup mRgGenere;
    private RadioButton mRdbMale;
    private RadioButton mRdbFemale;
    private RadioButton mRdbOther;

    private Spinner mSpnOfici;
    private Spinner mSpnRasa;

    private List<Ofici> mLlistaOfici;
    private List<Rasa> mLlistaRases;
    private List<Imatge> mLlistaImatges;
    private List<Personatge> mLlistaPersonatges;

    private ImageView mImvAnteriorPersonatge;
    private ImageView mImvSeguentPersonatge;
    private ImageView mImvFotoPersonatge;
    private ImageView mImvAnteriorImatge;
    private ImageView mImvSeguentImatge;
    private ImageView mImvVisualitzarPersonatge;
    private ImageView mImvNouPersonatge;

    private EditText mEdtNom;
    private EditText mEdtDescripcio;

    private SeekBar mSbStrenght;
    private SeekBar mSbDexterity;
    private SeekBar mSbConstitution;
    private SeekBar mSbIntelligence;
    private SeekBar mSbWisdom;
    private SeekBar mSbCharisma;

    ListIterator<Personatge> lItPersonatge;
    int CanviSentitPersonatge = -1;

    ListIterator<Imatge> lItImatge;
    int CanviSentitImatge = -1;

    Personatge PersonatgeActual = null;
    Boolean PersonatgeNou = false;

    private void BuscarViewById() {

        mImvAnteriorPersonatge = (ImageView) findViewById(R.id.imvAnteriorPersonatge);
        mImvSeguentPersonatge = (ImageView) findViewById(R.id.imvSeguentPersonatge);
        mImvFotoPersonatge = (ImageView) findViewById(R.id.imvFotoEscollir);
        mImvAnteriorImatge = (ImageView) findViewById(R.id.imvImatgeAnterior);
        mImvSeguentImatge = (ImageView) findViewById(R.id.imvImatgeSeguent);
        mImvVisualitzarPersonatge = (ImageView) findViewById(R.id.imvVisualitzarPersonatge);
        mImvNouPersonatge = (ImageView) findViewById(R.id.imvNouPersonatge);

        mEdtNom = (EditText) findViewById(R.id.edtNom);
        mEdtDescripcio = (EditText) findViewById(R.id.edtDescripcio);

        mRgGenere = (RadioGroup) findViewById(R.id.rgGenere);
        mRgTipus = (RadioGroup) findViewById(R.id.rgTipus);

        mRdbMale = (RadioButton) findViewById(R.id.rbMale);
        mRdbFemale = (RadioButton) findViewById(R.id.rbFemale);
        mRdbOther = (RadioButton) findViewById(R.id.rbOther);
        mRdbAlliance = (RadioButton) findViewById(R.id.rbAlliance);
        mRdbHorde = (RadioButton) findViewById(R.id.rbHorde);
        mRdbNeutral = (RadioButton) findViewById(R.id.rbNeutral);

        mSpnOfici = (Spinner) findViewById(R.id.spOfici);
        mSpnRasa = (Spinner) findViewById(R.id.spRasa);

        mSbStrenght = (SeekBar) findViewById(R.id.sbStrenght);
        mSbDexterity = (SeekBar) findViewById(R.id.sbDexterity);
        mSbConstitution = (SeekBar) findViewById(R.id.sbConstitution);
        mSbIntelligence = (SeekBar) findViewById(R.id.sbIntelligence);
        mSbWisdom = (SeekBar) findViewById(R.id.sbWisdom);
        mSbCharisma = (SeekBar) findViewById(R.id.sbCharisma);

    }

    private void DefinirEvntsDeLesView() {

        SeguentPersonatge Seguent = new SeguentPersonatge();
        mImvAnteriorPersonatge.setOnClickListener(Seguent);
        mImvSeguentPersonatge.setOnClickListener(Seguent);

        SeguentImatge seguentImatge = new SeguentImatge();
        mImvAnteriorImatge.setOnClickListener(seguentImatge);
        mImvSeguentImatge.setOnClickListener(seguentImatge);

        mEdtNom.addTextChangedListener(new EditableTextWatcher(mEdtNom));
        mEdtDescripcio.addTextChangedListener(new EditableTextWatcher(mEdtDescripcio));

        mRgGenere.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rdoSelected = (RadioButton) findViewById(i);
                PersonatgeActual.setSexe(Sexe.valueOf(rdoSelected.getText().toString().toUpperCase()));
            }

        });

        mRgTipus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rdoSelected = (RadioButton) findViewById(i);
                carregarSpinnerRasa(rdoSelected);
            }
        });

        mSpnOfici.setOnItemSelectedListener(new ItemSpinner());
        mSpnRasa.setOnItemSelectedListener(new ItemSpinner());

        EstadistiquesPersonatge estadistiquesPersonatge = new EstadistiquesPersonatge();
        mSbStrenght.setOnSeekBarChangeListener(estadistiquesPersonatge);
        mSbDexterity.setOnSeekBarChangeListener(estadistiquesPersonatge);
        mSbConstitution.setOnSeekBarChangeListener(estadistiquesPersonatge);
        mSbIntelligence.setOnSeekBarChangeListener(estadistiquesPersonatge);
        mSbWisdom.setOnSeekBarChangeListener(estadistiquesPersonatge);
        mSbCharisma.setOnSeekBarChangeListener(estadistiquesPersonatge);

        mImvVisualitzarPersonatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityVisualitzar av = new ActivityVisualitzar();
                Intent intent = new Intent(MainActivity.this, av.getClass());
                intent.putExtra("Personatge", PersonatgeActual);
                startActivity(intent);
            }
        });


        mImvNouPersonatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personatge p = new Personatge();
                PersonatgeNou = true;
                mostrarPersonatge(p);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Busquem TOTES les View
        BuscarViewById();

        //Definim els events
        DefinirEvntsDeLesView();

        mLlistaPersonatges = Personatge.getPersonatges();
        lItPersonatge = mLlistaPersonatges.listIterator();
        mLlistaImatges = Imatge.getImatges();
        lItImatge = mLlistaImatges.listIterator();

        iniciarAplicacio();

    }

    private void iniciarAplicacio() {
        carregarSpinnerOfici();
       // mImvFotoPersonatge.setImageResource(R.drawable.lk);
        mostrarPersonatge(lItPersonatge.next());

    }

    private void carregarSpinnerOfici() {

        mLlistaOfici = new ArrayList<>(Ofici.getOficis());

        ArrayAdapter<Ofici> adapterLlistaTipus1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLlistaOfici);
        String prompt = "Selecciona..";

        //mSpnOfici.setAdapter(new NothingSelectedSpinnerAdapter(prompt, adapterLlistaTipus1, R.layout.contact_spinner_row_nothing_selected, this));

        //mSpnOfici.setPrompt("aaaaa");
        mSpnOfici.setAdapter(adapterLlistaTipus1);

    }

    private void carregarSpinnerRasa(RadioButton i) {

        switch (i.getId()) {
            case R.id.rbAlliance:
                mLlistaRases = Rasa.getRaces(Alignment.ALLIANCE);
                break;

            case R.id.rbNeutral:
                mLlistaRases = Rasa.getRaces(Alignment.NEUTRAL);
                break;

            case R.id.rbHorde:
                mLlistaRases = Rasa.getRaces(Alignment.HORDE);
                break;
        }
        ArrayAdapter<Rasa> adapterLlistaRasa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mLlistaRases);
        mSpnRasa.setAdapter(adapterLlistaRasa);
        //mSpnRasa.setAdapter(new NothingSelectedSpinnerAdapter("Selecciona", adapterLlistaRasa, R.layout.contact_spinner_row_nothing_selected, this));
    }

    private void mostrarPersonatge(Personatge p) {

        PersonatgeActual = p;

        mEdtNom.setText(p.getNom());

        switch (p.getSexe()) {
            case MALE:
                mRdbMale.setChecked(true);
                break;
            case FEMALE:
                mRdbFemale.setChecked(true);
                break;
            case OTHER:
                mRdbOther.setChecked(true);
                break;
        }

        switch (p.getAlignement()) {
            case ALLIANCE:
                mRdbAlliance.setChecked(true);
                break;
            case NEUTRAL:
                mRdbNeutral.setChecked(true);
                break;
            case HORDE:
                mRdbHorde.setChecked(true);
                break;
        }

        mSpnOfici.setSelection(mLlistaOfici.indexOf(PersonatgeActual.getOfici()));
        mSpnRasa.setSelection(mLlistaRases.indexOf(PersonatgeActual.getRasa()));

        mSbStrenght.setProgress(p.getStrength());
        mSbDexterity.setProgress(p.getDexterity());
        mSbConstitution.setProgress(p.getConstitution());
        mSbIntelligence.setProgress(p.getIntelligence());
        mSbWisdom.setProgress(p.getWisdom());
        mSbCharisma.setProgress(p.getCharisma());

        mImvFotoPersonatge.setImageResource(p.getImage());
        mEdtDescripcio.setText(p.getDescription());

    }

    class SeguentPersonatge implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (PersonatgeNou) PersonatgeNou = false;

            switch (view.getId()) {
                case R.id.imvSeguentPersonatge:

                    if (CanviSentitPersonatge == -1) {
                        CanviSentitPersonatge = 1;
                    }

                    if (lItPersonatge.hasNext()) {
                        mostrarPersonatge(lItPersonatge.next());
                        if (CanviSentitPersonatge == 0) {
                            CanviSentitPersonatge = 1;
                            mImvSeguentPersonatge.performClick();
                        }
                    } else {
                        lItPersonatge = mLlistaPersonatges.listIterator();
                        mostrarPersonatge(lItPersonatge.next());
                    }

                    break;

                case R.id.imvAnteriorPersonatge:

                    if (CanviSentitPersonatge == -1) {
                        CanviSentitPersonatge = 0;
                    }

                    if (lItPersonatge.hasPrevious()) {
                        mostrarPersonatge(lItPersonatge.previous());
                        if (CanviSentitPersonatge == 1) {
                            CanviSentitPersonatge = 0;
                            mImvAnteriorPersonatge.performClick();
                        }
                    } else {
                        lItPersonatge = mLlistaPersonatges.listIterator(mLlistaPersonatges.size());
                        mostrarPersonatge(lItPersonatge.previous());
                    }
                    break;
            }
        }
    }

    class SeguentImatge implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int imatge = R.drawable.lk; //Per inicialitzar la variable

            switch (view.getId()) {
                case R.id.imvImatgeSeguent:

                    if (CanviSentitImatge == -1) {
                        CanviSentitImatge = 1;
                    }

                    if (lItImatge.hasNext()) {
                        imatge = lItImatge.next().getImageResourceId();
                        mImvFotoPersonatge.setImageResource(imatge);
                        if (CanviSentitImatge == 0) {
                            CanviSentitImatge = 1;
                            mImvSeguentImatge.performClick();
                        }
                    } else {
                        lItImatge = mLlistaImatges.listIterator();
                        imatge = lItImatge.next().getImageResourceId();
                        mImvFotoPersonatge.setImageResource(imatge);
                    }

                    break;

                case R.id.imvImatgeAnterior:

                    if (CanviSentitImatge == -1) {
                        CanviSentitImatge = 0;
                    }

                    if (lItImatge.hasPrevious()) {
                        imatge = lItImatge.previous().getImageResourceId();
                        mImvFotoPersonatge.setImageResource(imatge);
                        if (CanviSentitImatge == 1) {
                            CanviSentitImatge = 0;
                            mImvAnteriorImatge.performClick();
                        }
                    } else {
                        lItImatge = mLlistaImatges.listIterator(mLlistaImatges.size());
                        imatge = lItImatge.previous().getImageResourceId();
                        mImvFotoPersonatge.setImageResource(imatge);
                    }
                    break;
            }

            PersonatgeActual.setImage(imatge);
        }
    }

    class EstadistiquesPersonatge implements SeekBar.OnSeekBarChangeListener {

        private int nivell;

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            nivell = i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            switch (seekBar.getId()) {

                case R.id.sbStrenght:
                    PersonatgeActual.setStrength(nivell);
                    break;
                case R.id.sbDexterity:
                    PersonatgeActual.setDexterity(nivell);
                    break;
                case R.id.sbConstitution:
                    PersonatgeActual.setConstitution(nivell);
                    break;
                case R.id.sbIntelligence:
                    PersonatgeActual.setIntelligence(nivell);
                    break;
                case R.id.sbWisdom:
                    PersonatgeActual.setWisdom(nivell);
                    break;
                case R.id.sbCharisma:
                    PersonatgeActual.setCharisma(nivell);
                    break;
            }
        }
    }

    class ItemSpinner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            switch (adapterView.getId()) {
                case R.id.spOfici:
                    Ofici ofici = (Ofici) adapterView.getSelectedItem();
                    PersonatgeActual.setOfici(ofici);
                    break;
                case R.id.spRasa:
                    Rasa rasa = (Rasa) adapterView.getSelectedItem();
                    PersonatgeActual.setRasa(rasa);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class EditableTextWatcher implements TextWatcher {

        private int Id;

        public EditableTextWatcher(View view) {
            Id = view.getId();
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (Id) {
                case R.id.edtNom:
                    PersonatgeActual.setNom(editable.toString());
                    if (PersonatgeNou && mEdtNom.getText().length() > 1) {
                        mLlistaPersonatges.add(PersonatgeActual);
                        PersonatgeNou = false;
                        lItPersonatge = mLlistaPersonatges.listIterator(mLlistaPersonatges.size());
                    }
                    break;
                case R.id.edtDescripcio:
                    PersonatgeActual.setDescripcio(editable.toString());
                    break;
            }
        }
    }
}
