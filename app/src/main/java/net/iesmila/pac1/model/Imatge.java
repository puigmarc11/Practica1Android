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
    public static List<Imatge> getImatges() {

        if( llistaImatges==null) {
            llistaImatges = new ArrayList<Imatge>();
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.be));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.orc));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.lk));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.hm));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.sylvanas));
            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.enano));
            // llistaImatges.add(new Imatge(Sexe.MALE, Rasa.HUMAN, R.drawable.human1));
            // llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.DRAGON, R.drawable.dragon));
        }
        return llistaImatges;
    }


    public List<Imatge> getLlistaImatges(){
        return llistaImatges;
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
