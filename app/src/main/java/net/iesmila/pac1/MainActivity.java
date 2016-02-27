package net.iesmila.pac1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

        //Fletxes per canviar de personatge
        SeguentPersonatge Seguent = new SeguentPersonatge();
        mImvAnteriorPersonatge.setOnClickListener(Seguent);
        mImvSeguentPersonatge.setOnClickListener(Seguent);

        //Fletxes per canviar d'imatges
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
                carregarImatges(PersonatgeActual.getSexe(), PersonatgeActual.getRasa());
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

        //Envent per llençar el nou Activity
        mImvVisualitzarPersonatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityVisualitzar av = new ActivityVisualitzar();
                Intent intent = new Intent(MainActivity.this, ActivityVisualitzar.class);
                intent.putExtra("Personatge", PersonatgeActual);
                startActivity(intent);
            }
        });

        //Crear un nou personatge
        mImvNouPersonatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Personatge p = new Personatge();
                PersonatgeNou = true;
                //Iniciem el personatge amb dades per no tenir problemes de NullPointer...
                //Aquest personatge encara no s'afegeix a la llista per evitar crear molts personatges seguits.
                //s'afegira a la llista un cop haguem introduit un nom.
                p.setSexe(Sexe.MALE);
                p.setRasa(Rasa.getRasaPerCodi(1, Alignment.ALLIANCE));
                p.setOfici(Ofici.getOficiPerCodi(1));
                p.setImage(0);
                Log.i("Personatge","Nou Personatge --> Imatge" + p.getImage());
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

        iniciarAplicacio();

    }

    private void iniciarAplicacio() {
        mLlistaPersonatges = Personatge.getPersonatges();
        lItPersonatge = mLlistaPersonatges.listIterator();
        carregarSpinnerOfici();
        mostrarPersonatge(lItPersonatge.next());
    }

    private void carregarImatges(Sexe mSexe, Rasa mRasa) {

        mLlistaImatges = Imatge.getImages(mSexe, mRasa);
        if (mLlistaImatges.size() > 1) {
            int i;
            //for(i = 0; i<mLlistaImatges.size() && mLlistaImatges.get(i).getImageResourceId() != PersonatgeActual.getImage(); i++);
            //Log.i("Personatge","Imatge " + String.valueOf(i));
            lItImatge = mLlistaImatges.listIterator();
            CanviSentitImatge = -1;
            mImvSeguentImatge.performClick();
        } else if (mLlistaImatges.size() == 1) {
            //Optimitzacio per si la llista d'imatges només en té una
            mImvFotoPersonatge.setImageResource(mLlistaImatges.get(0).getImageResourceId());
            PersonatgeActual.setImage(mLlistaImatges.get(0).getImageResourceId());
        } else {
            lItImatge = null;
            PersonatgeActual.setImage(0);
            mImvFotoPersonatge.setImageResource(0);
        }
    }

    //Carrega els oficis al Spinner
    private void carregarSpinnerOfici() {
        mLlistaOfici = new ArrayList<>(Ofici.getOficis());
        ArrayAdapter<Ofici> adapterLlistaOficis = new ArrayAdapter<>(this, android.R.layout.preference_category, mLlistaOfici);
        mSpnOfici.setAdapter(adapterLlistaOficis);
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

        ArrayAdapter<Rasa> adapterLlistaRasa = new ArrayAdapter<>(this, android.R.layout.preference_category, mLlistaRases);
        mSpnRasa.setAdapter(adapterLlistaRasa);
    }

    private void mostrarPersonatge(Personatge p) {

        PersonatgeActual = p;

        if (PersonatgeActual.getNom() != null) {
            mEdtNom.setText(PersonatgeActual.getNom());
        } else {
            mEdtNom.setText("");
        }

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

        if (PersonatgeActual.getRasa() != null) {
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
            mSpnRasa.setSelection(mLlistaRases.indexOf(PersonatgeActual.getRasa()));
        }

        mSpnOfici.setSelection(mLlistaOfici.indexOf(PersonatgeActual.getOfici()));

        mSbStrenght.setProgress(p.getStrength());
        mSbDexterity.setProgress(p.getDexterity());
        mSbConstitution.setProgress(p.getConstitution());
        mSbIntelligence.setProgress(p.getIntelligence());
        mSbWisdom.setProgress(p.getWisdom());
        mSbCharisma.setProgress(p.getCharisma());

        //Si fem Personatges nous seguits, (a paritr del segon), aquets no fan saltar events dels radio buttons change, pertant
        //no s'actualitza la llista d'imatges i la forçem aqui al final abans de seleccionar la imatge.
        if(PersonatgeNou){
            carregarImatges(PersonatgeActual.getSexe(),PersonatgeActual.getRasa());
        }
        mImvFotoPersonatge.setImageResource(PersonatgeActual.getImage());

        mEdtDescripcio.setText(p.getDescription());

    }

    //Classe per canviar de personatges.
    //Conte un flag per saber si és canvia la direcció del llistat(Hem de realitzar un doble next//previous
    //Si s'arriva a un dels dos extrems es torna a generar l'itarador. O bé pel principi de la llista o bé per el final. Serveix per tenir la llista circular.
    class SeguentPersonatge implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Boolean reiniciarIterator = false;
            if (PersonatgeNou) PersonatgeNou = false;

            switch (view.getId()) {
                case R.id.imvSeguentPersonatge:

                    if (CanviSentitPersonatge == -1) CanviSentitPersonatge = 1;

                    if (lItPersonatge.hasNext()) {
                        if (CanviSentitPersonatge == 0) {
                            CanviSentitPersonatge = 1;
                            lItPersonatge.next();
                            if (lItPersonatge.hasNext()) mostrarPersonatge(lItPersonatge.next());
                            else reiniciarIterator = true;
                        } else mostrarPersonatge(lItPersonatge.next());
                    } else reiniciarIterator = true;

                    if (reiniciarIterator) {
                        lItPersonatge = mLlistaPersonatges.listIterator();
                        mostrarPersonatge(lItPersonatge.next());
                    }
                    break;

                case R.id.imvAnteriorPersonatge:

                    if (CanviSentitPersonatge == -1) CanviSentitPersonatge = 0;

                    if (lItPersonatge.hasPrevious()) {
                        if (CanviSentitPersonatge == 1) {
                            CanviSentitPersonatge = 0;
                            lItPersonatge.previous();
                            if (lItPersonatge.hasPrevious())
                                mostrarPersonatge(lItPersonatge.previous());
                            else reiniciarIterator = true;
                        } else mostrarPersonatge(lItPersonatge.previous());
                    } else reiniciarIterator = true;

                    if (reiniciarIterator) {
                        lItPersonatge = mLlistaPersonatges.listIterator(mLlistaPersonatges.size());
                        mostrarPersonatge(lItPersonatge.previous());
                    }
                    break;
            }
        }
    }

    //Classe per canviar d'imatges de la llista per escollir.
    //Funciona similar que la del personatge, però separades per diferènciar còdi.
    class SeguentImatge implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            //Evitem recorre el switch si no hi ha imatges, o bé si la llista només en té una.
            if (lItImatge == null || mLlistaImatges.size() == 1 ) return;

            int imatge = 0; //Per inicialitzar la variable
            Boolean reiniciarIterator = false;

            switch (view.getId()) {
                case R.id.imvImatgeSeguent:

                    if (CanviSentitImatge == -1) CanviSentitImatge = 1;

                    if (lItImatge.hasNext()) {
                        if (CanviSentitImatge == 0) {
                            CanviSentitImatge = 1;
                            imatge = lItImatge.next().getImageResourceId();
                            if (lItImatge.hasNext()) imatge = lItImatge.next().getImageResourceId();
                            else reiniciarIterator = true;
                        } else imatge = lItImatge.next().getImageResourceId();
                    } else reiniciarIterator = true;

                    if (reiniciarIterator) {
                        lItImatge = mLlistaImatges.listIterator();
                        imatge = lItImatge.next().getImageResourceId();
                    }
                    break;

                case R.id.imvImatgeAnterior:

                    if (CanviSentitImatge == -1) CanviSentitImatge = 0;

                    if (lItImatge.hasPrevious()) {
                        if (CanviSentitImatge == 1) {
                            CanviSentitImatge = 0;
                            imatge = lItImatge.previous().getImageResourceId();
                            if (lItImatge.hasPrevious())
                                imatge = lItImatge.previous().getImageResourceId();
                            else reiniciarIterator = true;
                        } else imatge = lItImatge.previous().getImageResourceId();
                    } else reiniciarIterator = true;

                    if (reiniciarIterator) {
                        lItImatge = mLlistaImatges.listIterator(mLlistaImatges.size());
                        imatge = lItImatge.previous().getImageResourceId();
                    }
                    break;
            }

            mImvFotoPersonatge.setImageResource(imatge);
            PersonatgeActual.setImage(imatge);
        }
    }

    //Classe Per actualitzar les barres d'habilitats
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

    //Classe per actualitzar els spinners de ofici i rasa
    //També s'actualitza indirectament l'alignment ja que aquest va lligat a la rasa i només és gestiona en
    //un lloc per no tenir conflictes d'events
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
                    //TODO no en visualitzar persontge
                    carregarImatges(PersonatgeActual.getSexe(), PersonatgeActual.getRasa());
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    //Classe per actualitzar el nom i la descripció del personatge
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
                        Log.i("Personatge","Guardar personatge????");
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
