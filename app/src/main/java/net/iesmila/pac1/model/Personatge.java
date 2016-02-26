package net.iesmila.pac1.model;


import net.iesmila.pac1.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Personatge implements Serializable {

    String mNom;
    Rasa mRasa;
    Sexe mSexe;
    Ofici mOfici;
    int mImage;
    int mConstitution, mStrength, mDexterity, mWisdom, mIntelligence, mCharisma;
    String mDescription;

    private static ArrayList<Personatge> mPersonatges;

    public static List<Personatge> getPersonatges() {
        if (mPersonatges == null) {
            mPersonatges = new ArrayList<Personatge>();
//            mPersonatges.add(new Personatge("Sylvanas", Rasa.getRasaPerCodi(2, Alignment.HORDE), Sexe.FEMALE, Ofici.getOficiPerCodi(3), R.drawable.sylvanas, 50, 65, 10, 40, 20, 10, ""));
//            mPersonatges.add(new Personatge("Mago", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.FEMALE, Ofici.getOficiPerCodi(7), R.drawable.hm, 5, 77, 30, 40, 20, 10, ""));
//            mPersonatges.add(new Personatge("Orco", Rasa.getRasaPerCodi(1, Alignment.HORDE), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.orcf1, 10, 40, 30, 40, 20, 10, ""));
//            mPersonatges.add(new Personatge("Exanime", Rasa.getRasaPerCodi(1, Alignment.NEUTRAL), Sexe.MALE, Ofici.getOficiPerCodi(2), R.drawable.lk, 20, 60, 100, 40, 20, 10, ""));
//            mPersonatges.add(new Personatge("Enano", Rasa.getRasaPerCodi(3, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(3), R.drawable.enano, 20, 60, 100, 40, 20, 10, ""));
            mPersonatges.add(new Personatge("Orco1", Rasa.getRasaPerCodi(1, Alignment.HORDE), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.orcf1, 10, 40, 30, 40, 20, 10, ""));
            mPersonatges.add(new Personatge("Orco2", Rasa.getRasaPerCodi(1, Alignment.HORDE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.orcm1, 10, 40, 30, 40, 20, 10, ""));
            mPersonatges.add(new Personatge("Human1", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 10, 40, 30, 40, 20, 10, ""));
            mPersonatges.add(new Personatge("Human2", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.humanf2, 10, 40, 30, 40, 20, 10, ""));
            mPersonatges.add(new Personatge("Draenei", Rasa.getRasaPerCodi(2, Alignment.ALLIANCE), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.draeneif3, 10, 40, 30, 40, 20, 10, ""));
        }

        return mPersonatges;
    }

    public Personatge() {
    }

    public Personatge(String mNom, Rasa mRasa, Sexe mSexe, Ofici mOfici, int mImage, int mStrength, int mDexterity, int mConstitution, int mIntelligence, int mWisdom, int mCharisma, String mDescription) {
        this.mNom = mNom;
        this.mRasa = mRasa;
        this.mSexe = mSexe;
        this.mOfici = mOfici;
        this.mImage = mImage;
        this.mStrength = mStrength;
        this.mDexterity = mDexterity;
        this.mConstitution = mConstitution;
        this.mIntelligence = mIntelligence;
        this.mWisdom = mWisdom;
        this.mCharisma = mCharisma;
        this.mDescription = mDescription;
    }

    public String getNom() {
        return mNom;
    }

    public Sexe getSexe() {
        return mSexe;
    }

    public Ofici getOfici(){
        return mOfici;
    }

    public Alignment getAlignement() {
        if (mRasa != null) {
            return mRasa.getAlignment();
        }
        return null;
    }

    public Rasa getRasa() {
        return mRasa;
    }

    public int getStrength() {
        return mStrength;
    }

    public int getConstitution() {
        return mConstitution;
    }

    public int getDexterity() {
        return mDexterity;
    }

    public int getWisdom() {
        return mWisdom;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public int getCharisma() {
        return mCharisma;
    }

    public int getImage() {
        return mImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setNom(String mNom) {
        if (mNom.length() > 2) {
            this.mNom = mNom;
        }
    }

    public void setSexe(Sexe mSexe) {
        this.mSexe = mSexe;
    }

    public void setOfici(Ofici mOfici) {
        this.mOfici = mOfici;
    }

    public void setRasa(Rasa pRasa) {
        mRasa = pRasa;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public void setStrength(int mStrength) {
        this.mStrength = mStrength;
    }

    public void setDexterity(int mDexterity) {
        this.mDexterity = mDexterity;
    }

    public void setConstitution(int mConstitution) {
        this.mConstitution = mConstitution;
    }

    public void setIntelligence(int mIntelligence) {
        this.mIntelligence = mIntelligence;
    }

    public void setWisdom(int mWisdom) {
        this.mWisdom = mWisdom;
    }

    public void setCharisma(int mCharisma) {
        this.mCharisma = mCharisma;
    }

    public void setDescripcio(String mDescripcio) {
        this.mDescription = mDescripcio;
    }

    public int calcularNivell() {
        return (getStrength() + getDexterity() + getConstitution() + getIntelligence() + getWisdom() + getCharisma()) / 6;
    }
}
