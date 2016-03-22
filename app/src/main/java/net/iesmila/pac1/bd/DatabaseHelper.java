package net.iesmila.pac1.bd;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import net.iesmila.pac1.model.Alignment;
import net.iesmila.pac1.model.Imatge;
import net.iesmila.pac1.model.Ofici;
import net.iesmila.pac1.model.Personatge;
import net.iesmila.pac1.model.Rasa;
import net.iesmila.pac1.model.Sexe;

import java.util.ArrayList;

/**
 * Created by Marc on 21/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(Context context, String name, int version) {
        super(context, name, null, 1);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("DB", "Creació de la base de dades");

//        try {

        //----------------------CREACIÓ DE LES TAULES------------------------//

        String TaulaOficis = "CREATE TABLE ofici (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT NOT NULL" +
                ");";

        db.execSQL(TaulaOficis);
        Log.i("DB", "Taula Oficis");

        //--------------------------------------------------------------------//

        String TaulaAlignment = "CREATE TABLE alignment (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT NOT NULL" +
                ");";

        db.execSQL(TaulaAlignment);
        Log.i("DB", "Taula Alignment");

        //--------------------------------------------------------------------//

        String TaulaRasa = "CREATE TABLE rasa (" +
                "id INTEGER," +
                "alignment INTEGER," +
                "nom TEXT NOT NULL," +
                "imatge TEXT," +
                "FOREIGN KEY (alignment) REFERENCES alignment(id)," +
                "PRIMARY KEY (id,alignment)" +
                ");";

        db.execSQL(TaulaRasa);
        Log.i("DB", "Taula Rasa");

        //--------------------------------------------------------------------//

        String TaulaImatge = "CREATE TABLE imatge (" +
                "drawable TEXT PRIMARY KEY," +
                "rasaId INTEGER," +
                "rasaAlignment INTEGER," +
                "sexe TEXT NOT NULL CHECK (sexe IN ('M', 'F','O'))," +
                "FOREIGN KEY (rasaId,rasaAlignment) REFERENCES rasa(id,alignment)" +
                ");";

        db.execSQL(TaulaImatge);
        Log.i("DB", "Taula Imatge");

        //--------------------------------------------------------------------//
        String TaulaPersonatge;

        TaulaPersonatge = "CREATE TABLE personatge (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "rasaId INTEGER," +
                "rasaAlignment INTEGER," +
                "sexe TEXT NOT NULL CHECK (sexe IN ('M', 'F','O'))," +
                "ofici INTEGER," +
                "imatge TEXT NOT NULL," +
                "s INTEGER NOT NULL," +
                "d INTEGER NOT NULL," +
                "c INTEGER NOT NULL," +
                "i INTEGER NOT NULL," +
                "w INTEGER NOT NULL," +
                "ch INTEGER NOT NULL," +
                "descripcio TEXT," +
                "FOREIGN KEY (ofici) REFERENCES ofici(id)," +
                "FOREIGN KEY (rasaId,rasaAlignment) REFERENCES rasa(id,alignment)," +
                "FOREIGN KEY (imatge) REFERENCES imatge(drawable)" +
                ");";

        db.execSQL(TaulaPersonatge);
        Log.i("DB", "Taula Personatge");

        //-----------------------INSERCIÓ DE VALORS--------------------------//

        String OficisInicials[] = new String[9];
        OficisInicials[0] = "Insert into ofici values(null,'Guerrero')";
        OficisInicials[1] = "Insert into ofici values(null,'Paladin')";
        OficisInicials[2] = "Insert into ofici values(null,'Cazador')";
        OficisInicials[3] = "Insert into ofici values(null,'Picaro')";
        OficisInicials[4] = "Insert into ofici values(null,'Sacerdote')";
        OficisInicials[5] = "Insert into ofici values(null,'Chaman')";
        OficisInicials[6] = "Insert into ofici values(null,'Mago')";
        OficisInicials[7] = "Insert into ofici values(null,'Brujo')";
        OficisInicials[8] = "Insert into ofici values(null,'Druida')";

        for (int i = 0; i < OficisInicials.length; i++) {
            db.execSQL(OficisInicials[i], new String[]{});
        }

        //--------------------------------------------------------------------//

        String AlignmentInicial[] = new String[3];
        AlignmentInicial[0] = "Insert into alignment values(null,'ALLIANCE')";
        AlignmentInicial[1] = "Insert into alignment values(null,'HORDE')";
        AlignmentInicial[2] = "Insert into alignment values(null,'NEUTRAL')";

        for (int i = 0; i < AlignmentInicial.length; i++) {
            db.execSQL(AlignmentInicial[i], new String[]{});
        }

        //--------------------------------------------------------------------//

        String RasaInicial[] = new String[10];
        RasaInicial[0] = "Insert into rasa values(1,1,'Humano','s3')";
        RasaInicial[1] = "Insert into rasa values(2,1,'Draenei','s3')";
        RasaInicial[2] = "Insert into rasa values(3,1,'Enano','s3')";
        RasaInicial[3] = "Insert into rasa values(4,1,'Elfo','s1')";

        RasaInicial[4] = "Insert into rasa values(1,2,'Orco','s4')";
        RasaInicial[5] = "Insert into rasa values(2,2,'Goblin','s2')";
        RasaInicial[6] = "Insert into rasa values(3,2,'Troll','s4')";
        RasaInicial[7] = "Insert into rasa values(4,2,'Undead','s4')";

        RasaInicial[8] = "Insert into rasa values(1,3,'Aspecto','s3')";
        RasaInicial[9] = "Insert into rasa values(2,3,'Dragon','s2')";

        for (int i = 0; i < RasaInicial.length; i++) {
            db.execSQL(RasaInicial[i], new String[]{});
        }

        //--------------------------------------------------------------------//

        ///// Alignment Horde /////
        String ImatgeInicial[] = new String[45];
        ImatgeInicial[0] = "Insert into imatge values('orcf1',1,2,'F')";
        ImatgeInicial[1] = "Insert into imatge values('orcm1',1,2,'M')";
        ImatgeInicial[2] = "Insert into imatge values('orcm2',1,2,'M')";
        ImatgeInicial[3] = "Insert into imatge values('orcm3',1,2,'M')";
        ImatgeInicial[4] = "Insert into imatge values('orcm4',1,2,'M')";

        ImatgeInicial[5] = "Insert into imatge values('goblinf1',2,2,'F')";
        ImatgeInicial[6] = "Insert into imatge values('goblinf2',2,2,'F')";
        ImatgeInicial[7] = "Insert into imatge values('goblinm1',2,2,'M')";
        ImatgeInicial[8] = "Insert into imatge values('goblinm2',2,2,'M')";
        ImatgeInicial[9] = "Insert into imatge values('goblinm3',2,2,'M')";

        ImatgeInicial[10] = "Insert into imatge values('trollf1',3,2,'F')";
        ImatgeInicial[11] = "Insert into imatge values('trollf2',3,2,'F')";
        ImatgeInicial[12] = "Insert into imatge values('trollm1',3,2,'M')";
        ImatgeInicial[13] = "Insert into imatge values('trollm2',3,2,'M')";

        ImatgeInicial[14] = "Insert into imatge values('undeadf1',4,2,'F')";
        ImatgeInicial[15] = "Insert into imatge values('undeadm1',4,2,'M')";

        ///// Alignment Alliance /////
        ImatgeInicial[16] = "Insert into imatge values('humanf1',1,1,'F')";
        ImatgeInicial[17] = "Insert into imatge values('humanf2',1,1,'F')";
        ImatgeInicial[18] = "Insert into imatge values('humanf3',1,1,'F')";
        ImatgeInicial[19] = "Insert into imatge values('humanf4',1,1,'F')";
        ImatgeInicial[20] = "Insert into imatge values('humanf5',1,1,'F')";
        ImatgeInicial[21] = "Insert into imatge values('humanm1',1,1,'M')";
        ImatgeInicial[22] = "Insert into imatge values('humanm2',1,1,'M')";
        ImatgeInicial[23] = "Insert into imatge values('humanm3',1,1,'M')";

        ImatgeInicial[24] = "Insert into imatge values('draeneif1',2,1,'F')";
        ImatgeInicial[25] = "Insert into imatge values('draeneif2',2,1,'F')";
        ImatgeInicial[26] = "Insert into imatge values('draeneif3',2,1,'F')";
        ImatgeInicial[27] = "Insert into imatge values('draeneim1',2,1,'M')";
        ImatgeInicial[28] = "Insert into imatge values('draeneim2',2,1,'M')";
        ImatgeInicial[29] = "Insert into imatge values('draeneim3',2,1,'M')";

        ImatgeInicial[30] = "Insert into imatge values('enanof1',3,1,'F')";
        ImatgeInicial[31] = "Insert into imatge values('enanom1',3,1,'M')";
        ImatgeInicial[32] = "Insert into imatge values('enanom2',3,1,'M')";
        ImatgeInicial[33] = "Insert into imatge values('enanom3',3,1,'M')";

        ImatgeInicial[34] = "Insert into imatge values('elff1',4,1,'F')";
        ImatgeInicial[35] = "Insert into imatge values('elff2',4,1,'F')";
        ImatgeInicial[36] = "Insert into imatge values('elfm1',4,1,'M')";
        ImatgeInicial[37] = "Insert into imatge values('elfm2',4,1,'M')";
        ImatgeInicial[38] = "Insert into imatge values('elfm3',4,1,'M')";

        ///// Alignment Neutral /////
        ImatgeInicial[39] = "Insert into imatge values('aspectof1',1,3,'F')";
        ImatgeInicial[40] = "Insert into imatge values('aspectof2',1,3,'F')";
        ImatgeInicial[41] = "Insert into imatge values('aspectom1',1,3,'M')";
        ImatgeInicial[42] = "Insert into imatge values('aspectom2',1,3,'M')";

        ImatgeInicial[43] = "Insert into imatge values('dragon1',2,3,'O')";
        ImatgeInicial[44] = "Insert into imatge values('dragon2',2,3,'O')";

        for (int i = 0; i < ImatgeInicial.length; i++) {
            db.execSQL(ImatgeInicial[i], new String[]{});
        }

        //--------------------------------------------------------------------//

        String valorsInicials[] = new String[5];

        valorsInicials[0] = "Insert into personatge values(null,'Jaina',1,1,'F',7,'humanf4',30,45,20,70,50,15,'Jaina Proudmoore is the founder and former Lady of Theramore Isle (as well as its only leader during its brief existence), the Alliance''s major port in southern Kalimdor. After the destruction of Theramore, she took leadership of the Kirin Tor. She is the daughter of Grand Admiral Daelin Proudmoore, sister of Tandred Proudmoore and Derek Proudmoore, and the alleged half-sister of Finnall Goldensword. She is also the most powerful human sorceress on Azeroth.')";
        valorsInicials[1] = "Insert into personatge values(null,'Prophet Velen',2,1,'M',7,'draeneim3',60,25,20,30,50,70,'Velen has been the leader of the draenei people since their flight from Argus 25,000 years before the first orcish invasion of Azeroth. As his title entails, he has been granted the gift of prophecy, and — aided by the Light — has guided his people as they fled from, and later fought against, the Burning Legion who had ensnared their eredar brethren. He is first among the draenei and is the nemesis (formerly best friend) of Kil''jaeden.')";
        valorsInicials[2] = "Insert into personatge values(null,'Thrall',1,2,'M',6,'orcm4',50,15,60,30,50,30,'Thrall, son of Durotan, was the Warchief of the restored orcish Horde and ruler of the red land of Durotar in Kalimdor. Before the Cataclysm, he was Warchief of all the Horde, but temporarily gave leadership to Garrosh Hellscream in order to lead the Earthen Ring''s effort with Farseer Nobundo against the Twilight''s Hammer cult and elemental imbalance afflicting Azeroth.')";
        valorsInicials[3] = "Insert into personatge values(null,'Sylvanas',4,2,'F',3,'undeadf1',40,15,80,70,30,40,'Lady Sylvanas Windrunner, styled variously as \"the Dark Lady\" and \"the Banshee Queen\", is the supreme ruler of the Forsaken, one of the most powerful factions of undead on Azeroth. In life, Sylvanas was the ranger-general of Silvermoon, whose leadership acumen and martial prowess were without equal. ')";
        valorsInicials[4] = "Insert into personatge values(null,'Alextrasza',2,3,'O',1,'dragon1',80,45,90,90,60,45,'Alexstrasza the Life-Binder, Aspect of the red dragonflight, is the guardian of all life in the world of Azeroth. She was one of five great dragons chosen by the titans to be empowered with a portion of the Pantheon''s power and rule over her flight while they watched over Azeroth and its inhabitants.')";

        for (int i = 0; i < valorsInicials.length; i++) {
            db.execSQL(valorsInicials[i], new String[]{});
        }

        //--------------------------------------------------------------------//
//        }catch (Exception e){
//            Log.e("E","Error en la DB meva.." + e);
//        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        Log.i("DB", "Actualitzant les taules..");
        db.execSQL("DROP TABLE IF EXISTS personatge");
        db.execSQL("DROP TABLE IF EXISTS ofici");
        db.execSQL("DROP TABLE IF EXISTS imatge");
        db.execSQL("DROP TABLE IF EXISTS rasa");
        db.execSQL("DROP TABLE IF EXISTS alignment");
        onCreate(db);

    }

    public void consultes() {

        ArrayList<Ofici> llistaO = new ArrayList<>();
        Cursor c1 = getReadableDatabase().rawQuery("select * from ofici", null);
        Log.i("DB", "-------OFICIS-------");
        while (c1.moveToNext()) {
            Log.i("DB", c1.getString(c1.getColumnIndex("id")) + " - " + c1.getString(c1.getColumnIndex("nom")));
            int id = c1.getInt(c1.getColumnIndex("id"));
            String nom = c1.getString(c1.getColumnIndex("nom"));
            llistaO.add(new Ofici(id, nom));
        }

        Ofici.setLlistaOficis(llistaO);

        //--------------------------------------------------------------------//

        ArrayList<Rasa> llistaRA = new ArrayList<>();
        ArrayList<Rasa> llistaRH = new ArrayList<>();
        ArrayList<Rasa> llistaRN = new ArrayList<>();
        c1 = getReadableDatabase().rawQuery("select * from rasa", null);
        Log.i("DB", "-------Races-------");
        while (c1.moveToNext()) {
            Log.i("DB", c1.getString(c1.getColumnIndex("id")) + " - " + c1.getString(c1.getColumnIndex("alignment")) + " - " + c1.getString(c1.getColumnIndex("nom")));
            int id = c1.getInt(c1.getColumnIndex("id"));
            int alignment = c1.getInt(c1.getColumnIndex("alignment"));
            String nom = c1.getString(c1.getColumnIndex("nom"));
            String  imatge = c1.getString(c1.getColumnIndex("imatge"));

            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(imatge, "drawable", context.getPackageName());

            switch (alignment) {
                case 1:
                    llistaRA.add(new Rasa(id, nom, Alignment.ALLIANCE,resourceId));
                    break;
                case 2:
                    llistaRH.add(new Rasa(id, nom, Alignment.HORDE,resourceId));
                    break;
                case 3:
                    llistaRN.add(new Rasa(id, nom, Alignment.NEUTRAL,resourceId));
                    break;
            }
        }

        Rasa.setLlistaRasa(llistaRA, Alignment.ALLIANCE);
        Rasa.setLlistaRasa(llistaRH, Alignment.HORDE);
        Rasa.setLlistaRasa(llistaRN, Alignment.NEUTRAL);

        //--------------------------------------------------------------------//

        ArrayList<Imatge> llistaImatges = new ArrayList<>();
        c1 = getReadableDatabase().rawQuery("select * from imatge", null);
        Log.i("DB", "-------OFICIS-------");
        while (c1.moveToNext()) {
            Log.i("DB", c1.getString(c1.getColumnIndex("drawable"))
                            + " - " + c1.getString(c1.getColumnIndex("rasaId"))
                            + " - " + c1.getString(c1.getColumnIndex("rasaAlignment"))
                            + " - " + c1.getString(c1.getColumnIndex("sexe"))
            );
            String drawable = c1.getString(c1.getColumnIndex("drawable"));
            int id = c1.getInt(c1.getColumnIndex("rasaId"));
            int alignment = c1.getInt(c1.getColumnIndex("rasaAlignment"));
            String sexe = c1.getString(c1.getColumnIndex("sexe"));

            Alignment a;
            if (alignment == 1) {
                a = Alignment.ALLIANCE;
            } else if (alignment == 2) {
                a = Alignment.HORDE;
            } else {
                a = Alignment.NEUTRAL;
            }

            Sexe sex;
            if (sexe.equals("M")) sex = Sexe.MALE;
            else if (sexe.equals("F")) sex = Sexe.FEMALE;
            else sex = Sexe.OTHER;

            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(drawable, "drawable", context.getPackageName());
            llistaImatges.add(new Imatge(sex, Rasa.getRasaPerCodi(id, a), resourceId));
        }

        Imatge.setLlistaImatges(llistaImatges);

        //--------------------------------------------------------------------//

        ArrayList<Personatge> llista = new ArrayList<>();

        c1 = getReadableDatabase().rawQuery("select * from personatge ", null);
        Log.i("DB", "----------PERSONATGES----------");
        while (c1.moveToNext()) {
            Log.i("DB", c1.getString(c1.getColumnIndex("id"))
                            + " - " + c1.getString(c1.getColumnIndex("nom"))
                            + " - " + c1.getString(c1.getColumnIndex("sexe"))
                            + " - " + c1.getString(c1.getColumnIndex("ofici"))
                            + " - " + c1.getString(c1.getColumnIndex("imatge"))
                            + " - " + c1.getString(c1.getColumnIndex("s"))
                            + " - " + c1.getString(c1.getColumnIndex("d"))
                            + " - " + c1.getString(c1.getColumnIndex("c"))
            );

            String nom = c1.getString(c1.getColumnIndex("nom"));
            String sexe = c1.getString(c1.getColumnIndex("sexe"));
            int ofici = c1.getInt(c1.getColumnIndex("ofici"));

            int rasa = c1.getInt(c1.getColumnIndex("rasaId"));
            int alignment = c1.getInt(c1.getColumnIndex("rasaAlignment"));

            String imatge = c1.getString(c1.getColumnIndex("imatge"));

            int s = c1.getInt(c1.getColumnIndex("s"));
            int d = c1.getInt(c1.getColumnIndex("d"));
            int c = c1.getInt(c1.getColumnIndex("c"));
            int i = c1.getInt(c1.getColumnIndex("i"));
            int w = c1.getInt(c1.getColumnIndex("w"));
            int ch = c1.getInt(c1.getColumnIndex("ch"));

            String descripcio = c1.getString(c1.getColumnIndex("descripcio"));

            Alignment a;
            if (alignment == 1) {
                a = Alignment.ALLIANCE;
            } else if (alignment == 2) {
                a = Alignment.HORDE;
            } else {
                a = Alignment.NEUTRAL;
            }

            Sexe sex;
            if (sexe.equals("M")) sex = Sexe.MALE;
            else if (sexe.equals("F")) sex = Sexe.FEMALE;
            else sex = Sexe.OTHER;

            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(imatge, "drawable", context.getPackageName());

            llista.add(new Personatge(nom, Rasa.getRasaPerCodi(rasa, a), sex, Ofici.getOficiPerCodi(ofici), resourceId, s, d, c, i, w, ch, descripcio));
        }

        Personatge.setLlistaPersonatges(llista);

        //--------------------------------------------------------------------//
    }

}

//        mPersonatges.add(new Personatge("111", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("222", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), humanm1, 50,50,50,50,50,50, ""));
//        mPersonatges.add(new Personatge("333", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("444", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("555", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 50,50,50,50,50,50, ""));
//        mPersonatges.add(new Personatge("666", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("777", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("888", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 50,50,50,50,50,50, ""));
//        mPersonatges.add(new Personatge("999", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 1,1,1,1,1,1, ""));
//        mPersonatges.add(new Personatge("000", Rasa.getRasaPerCodi(1, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.humanm1, 100,100,100,100,100,100, ""));
//        mPersonatges.add(new Personatge("Dwarf", Rasa.getRasaPerCodi(3, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.enanom1, 20, 43, 40, 10, 22, 77, ""));
//        mPersonatges.add(new Personatge("Elf", Rasa.getRasaPerCodi(4, Alignment.ALLIANCE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.elfm1, 20, 66, 40, 70, 22, 77, ""));
//        mPersonatges.add(new Personatge("Orc 1", Rasa.getRasaPerCodi(1, Alignment.HORDE), Sexe.MALE, Ofici.getOficiPerCodi(1), R.drawable.orcm4, 10, 40, 30, 60, 10, 20, ""));
//        mPersonatges.add(new Personatge("Orc 2", Rasa.getRasaPerCodi(1, Alignment.HORDE), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.orcf1, 10, 40, 30, 40, 20, 40, ""));
//        mPersonatges.add(new Personatge("Aspect", Rasa.getRasaPerCodi(1, Alignment.NEUTRAL), Sexe.FEMALE, Ofici.getOficiPerCodi(1), R.drawable.aspectof1, 70, 40, 50, 40, 20, 90, ""));

//            //Imatges Orco
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcf1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm3));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.HORDE), R.drawable.orcm4));

//            //Imatges Goblin
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinf1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinf2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.HORDE), R.drawable.goblinm3));

//            //Imatges Troll
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollf1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollf2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollm1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.HORDE), R.drawable.trollm2));

//            //Imatges Undead
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.HORDE), R.drawable.undeadf1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.HORDE), R.drawable.undeadm1));

//            //Imatges Humans
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf2));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf3));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf4));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanf5));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.ALLIANCE), R.drawable.humanm3));

//Imatges Draenei
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif2));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneif3));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(2,Alignment.ALLIANCE), R.drawable.draeneim3));

//            //Imatges Enano
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanof1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(3,Alignment.ALLIANCE), R.drawable.enanom3));
//
//            //Imatges Elfo
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elff1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elff2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(4,Alignment.ALLIANCE), R.drawable.elfm3));

//            //Imatges aspectos
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspectof1));
//            llistaImatges.add(new Imatge(Sexe.FEMALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspectof2));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspectom1));
//            llistaImatges.add(new Imatge(Sexe.MALE, Rasa.getRasaPerCodi(1,Alignment.NEUTRAL), R.drawable.aspectom2));
//
//            //Imatges Dragon
//            llistaImatges.add(new Imatge(Sexe.OTHER, Rasa.getRasaPerCodi(2,Alignment.NEUTRAL), R.drawable.dragon1));
//            llistaImatges.add(new Imatge(Sexe.OTHER, Rasa.getRasaPerCodi(2,Alignment.NEUTRAL), R.drawable.dragon2));