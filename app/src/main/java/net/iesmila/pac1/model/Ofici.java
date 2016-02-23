package net.iesmila.pac1.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Ofici implements Serializable{

    private int mCodi;
    private String mOfici;
    public static ArrayList<Ofici> mOficis;

    public Ofici(int mCodi, String mOfici) {
        this.mCodi = mCodi;
        this.mOfici = mOfici;
    }

    public int getCodi() {
        return mCodi;
    }

    public String getOfici() {
        return mOfici;
    }

    public void setOfici(String mOfici) {
        this.mOfici = mOfici;
    }

    public static ArrayList<Ofici> getOficis(){
        if (mOficis == null){
            mOficis = new ArrayList<>();
            mOficis.add(new Ofici(1,"Guerrero"));
            mOficis.add(new Ofici(2,"Paladin"));
            mOficis.add(new Ofici(3,"Cazador"));
            mOficis.add(new Ofici(4,"Picaro"));
            mOficis.add(new Ofici(5,"Sacerdote"));
            mOficis.add(new Ofici(6,"Chaman"));
            mOficis.add(new Ofici(7,"Mago"));
            mOficis.add(new Ofici(8,"Brujo"));
            mOficis.add(new Ofici(9,"Druida"));
        }
        return mOficis;
    }
    public static Ofici getOficiPerCodi(int codi){
        for(Ofici o: getOficis()){
            if(o.getCodi() == codi) return o;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return mOfici;
    }
}
