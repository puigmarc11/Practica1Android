package net.iesmila.pac1.model;


import android.widget.ArrayAdapter;

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

    private static ArrayList<Personatge> mPersonatges = new ArrayList<>();

    public static List<Personatge> getPersonatges() {
        return mPersonatges;
    }

    public static void setLlistaPersonatges(ArrayList<Personatge> llista){
        mPersonatges = llista;
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

    public Ofici getOfici() {
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
            this.mNom = mNom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personatge that = (Personatge) o;

        if (mImage != that.mImage) return false;
        if (mNom != null ? !mNom.equals(that.mNom) : that.mNom != null) return false;
        if (mRasa != null ? !mRasa.equals(that.mRasa) : that.mRasa != null) return false;
        if (mSexe != that.mSexe) return false;
        return !(mOfici != null ? !mOfici.equals(that.mOfici) : that.mOfici != null);

    }

    @Override
    public int hashCode() {
        int result = mNom != null ? mNom.hashCode() : 0;
        result = 31 * result + (mRasa != null ? mRasa.hashCode() : 0);
        result = 31 * result + (mSexe != null ? mSexe.hashCode() : 0);
        result = 31 * result + (mOfici != null ? mOfici.hashCode() : 0);
        result = 31 * result + mImage;
        return result;
    }
}
