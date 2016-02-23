package net.iesmila.pac1.model;

/*
public enum Rasa {
    ELF, HUMAN, ORC, DRAGON, DWARF
}*/

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rasa implements Serializable{

    private static List<Rasa> mLlistaRacesAlliance;
    private static List<Rasa> mLlistaRacesHorde;
    private static List<Rasa> mLlistaRacesNeutral;

    private int mCodi;
    private String mRasa;
    private Alignment mAlignment;

    public Rasa(int mCodi, String mRasa, Alignment mAlignment) {
      /*  if (!setRasa(mRasa,mAlignment)){
            throw new RuntimeException("Rasa i Alignment no son compatibles...");
        }*/
        this.mCodi = mCodi;
        this.mRasa = mRasa;
        this.mAlignment = mAlignment;
    }

    public String getRasa() {
        return mRasa;
    }

    public int getCodi(){
        return mCodi;
    }

    public Alignment getAlignment() {
        return mAlignment;
    }

//    public boolean setRasa(String mRasa, Alignment mAlignment) {
//        if (Rasa.rasaCorrecta(mRasa, mAlignment)){
//            this.mRasa = mRasa;
//            this.mAlignment = mAlignment;
//            return true;
//        }
//        return false;
//    }

    public static List<Rasa> getRaces(Alignment alignment) {

        switch (alignment) {
            case ALLIANCE:
                if (mLlistaRacesAlliance == null) {
                    mLlistaRacesAlliance = new ArrayList<>();
                    mLlistaRacesAlliance.add(new Rasa(1,"Humano",Alignment.ALLIANCE));
                    mLlistaRacesAlliance.add(new Rasa(2,"Draenei",Alignment.ALLIANCE));
                    mLlistaRacesAlliance.add(new Rasa(3,"Enano",Alignment.ALLIANCE));
                    mLlistaRacesAlliance.add(new Rasa(4,"Elfo",Alignment.ALLIANCE));
                }
                return mLlistaRacesAlliance;

            case HORDE:
                if (mLlistaRacesHorde == null) {
                    mLlistaRacesHorde = new ArrayList<>();
                    mLlistaRacesHorde.add(new Rasa(1,"Orco", Alignment.HORDE));
                    mLlistaRacesHorde.add(new Rasa(2,"No muerto", Alignment.HORDE));
                    mLlistaRacesHorde.add(new Rasa(3,"Tauren", Alignment.HORDE));
                    mLlistaRacesHorde.add(new Rasa(4,"Troll", Alignment.HORDE));
                }
                return mLlistaRacesHorde;

            case NEUTRAL:
                if (mLlistaRacesNeutral == null) {
                    mLlistaRacesNeutral = new ArrayList<>();
                    mLlistaRacesNeutral.add(new Rasa(1,"Pandaren",Alignment.NEUTRAL));
                }
                return mLlistaRacesNeutral;

            default:
                throw new RuntimeException("Tipus Erroni");
        }
    }

    public static boolean rasaCorrecta(String mRasa, Alignment mAlignment){

        List<Rasa> rases = getRaces(mAlignment);

        for (Rasa s : rases){
            if (s.equals(mRasa)){
                return true;
            }
        }
        return false;
    }

    public static Rasa getRasaPerCodi(int codi, Alignment mAlignment){

        List<Rasa> llista = getRaces(mAlignment);
        for(Rasa r : llista){
            if(r.getCodi() == codi){
                return r;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return mRasa;
   }
}



