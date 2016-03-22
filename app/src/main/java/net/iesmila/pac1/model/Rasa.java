package net.iesmila.pac1.model;


import android.support.v4.app.INotificationSideChannel;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rasa implements Serializable {

    private static List<Rasa> mLlistaRacesAlliance;
    private static List<Rasa> mLlistaRacesHorde;
    private static List<Rasa> mLlistaRacesNeutral;

    private static List<Integer> mLlistaImatges;

    private int mCodi;
    private String mRasa;
    private Alignment mAlignment;
    private int mImatge;

    public Rasa(int mCodi, String mRasa, Alignment mAlignment, int mImatge) {
        this.mCodi = mCodi;
        this.mRasa = mRasa;
        this.mAlignment = mAlignment;
        this.mImatge = mImatge;
    }

    public String getRasa() {
        return mRasa;
    }

    public int getCodi() {
        return mCodi;
    }

    public Alignment getAlignment() {
        return mAlignment;
    }

    public int getImatge(){return mImatge;}

    public static List<Rasa> getRaces(Alignment alignment) {

        switch (alignment) {
            case ALLIANCE:
                return mLlistaRacesAlliance;
            case HORDE:
                return mLlistaRacesHorde;
            case NEUTRAL:
                return mLlistaRacesNeutral;
            default:
                throw new RuntimeException("Tipus Erroni");
        }
    }

    public static void setLlistaRasa(ArrayList<Rasa> llistaR, Alignment a) {

        switch (a) {
            case ALLIANCE:
                mLlistaRacesAlliance = llistaR;
            case HORDE:
                mLlistaRacesHorde = llistaR;
            case NEUTRAL:
                mLlistaRacesNeutral = llistaR;
        }
    }

    public static Rasa getRasaPerCodi(int codi, Alignment mAlignment) {

        List<Rasa> llista = getRaces(mAlignment);
        for (Rasa r : llista) {
            if (r.getCodi() == codi) {
                return r;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return mRasa;
    }

    public static List<Integer> getAllImages(){
        return mLlistaImatges;
    }

}



