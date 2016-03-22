package net.iesmila.pac1.model;

import android.graphics.drawable.Drawable;

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
        return llistaImatges;
    }

    public static void setLlistaImatges(ArrayList<Imatge> llista){
        llistaImatges = llista;
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
