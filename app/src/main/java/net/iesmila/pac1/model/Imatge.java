package net.iesmila.pac1.model;

import net.iesmila.pac1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BERNAT on 05/02/2016.
 */
public class Imatge {

    private Sexe mSexe;
    private Rasa mRasa;
    private int mImageResourceId;

    private static ArrayList<Imatge> llistaImatges = null;
    public static List<Imatge> getAllImatges() {

        if( llistaImatges==null) {
            llistaImatges = new ArrayList<Imatge>();

            ///// Alignment Horde /////

            //Imatges Orco
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcf1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm2)); //TODO
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm3));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm4));

            //Imatges Goblin
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinf1));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinf2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm1));//TODO foto
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm3));//TODO foto

            //Imatges Troll
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollf1));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollf2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollm1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollm2));

            //Imatges Undead
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.HORDE), R.drawable.undeadf1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.HORDE), R.drawable.undeadm1));

            ///// Alignment Alliance /////

            //Imatges Humans
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf1));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf2));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf3));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf4));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf5));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm3));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm4));

            //Imatges Draenei
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif1));//TODO
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif2));//TODO
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif3));//TODO
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim3));

            //Imatges Enano
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanof1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom3));

            //Imatges Elfo
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elff1));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elff2));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm1));//TODO
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm2));//TODO
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm3));


            ///// Alignment Neutral /////

            //Imatges aspectos
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspecto1));
            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspecto2));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspecto3));

            //Imatges Dragon
            llistaImatges.add(new Imatge(Sexe.OTHER, Rasa.getRasaPerCodi(2,Alignment.NEUTRAL), R.drawable.dragon1));
            llistaImatges.add(new Imatge(Sexe.OTHER, Rasa.getRasaPerCodi(2,Alignment.NEUTRAL), R.drawable.dragon2));
            llistaImatges.add(new Imatge(Sexe.OTHER, Rasa.getRasaPerCodi(2,Alignment.NEUTRAL), R.drawable.dragon3));

        }
        return llistaImatges;
    }

    public static List<Imatge> getImages(Sexe mSexe, Rasa mRasa){

        List<Imatge> llistaFiltrada = new ArrayList<>();
        for(Imatge im: getAllImatges())
        {
            if (im.getSexe() == mSexe && im.getRasa().getRasa() == mRasa.getRasa()){
                llistaFiltrada.add(im);
            }
        }

        return llistaFiltrada;
    }

    public Imatge(Sexe mSexe, Rasa mRasa, int mImageResourceId) {
        this.mSexe = mSexe;
        this.mRasa = mRasa;
        this.mImageResourceId = mImageResourceId;
    }

    public Sexe getSexe() {
        return mSexe;
    }

    public void setSexe(Sexe mSexe) {
        this.mSexe = mSexe;
    }

    public Rasa getRasa() {
        return mRasa;
    }

    public void setRasa(Rasa mRasa) {
        this.mRasa = mRasa;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setmImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }
}
