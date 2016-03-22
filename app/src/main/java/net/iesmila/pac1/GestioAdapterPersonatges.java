package net.iesmila.pac1;

import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.iesmila.pac1.ActivityBotiga;
import net.iesmila.pac1.ActivityGestio;
import net.iesmila.pac1.R;
import net.iesmila.pac1.model.Alignment;
import net.iesmila.pac1.model.Personatge;
import net.iesmila.pac1.model.Sexe;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class GestioAdapterPersonatges extends ArrayAdapter<Personatge> implements Filterable {

    private List<Personatge> mLlistaPersonatges = null;
    private List<Personatge> mLlistaPersonatgesFiltrada = null;
    private ActivityGestio mActivity;
    private int index;

    private ItemFilter mFilter = new ItemFilter();

    public GestioAdapterPersonatges(final ActivityGestio context, final List<Personatge> mLlistaPersonatges) {
        super(context, R.layout.fila_gestio, R.id.txvNomGestioFila, mLlistaPersonatges);
        mActivity = context;
        this.mLlistaPersonatges = mLlistaPersonatges;
        mLlistaPersonatgesFiltrada = mLlistaPersonatges;
    }

    public int getCount() {
        return mLlistaPersonatgesFiltrada.size();
    }

    public Personatge getItem(int position) {
        return mLlistaPersonatgesFiltrada.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void removeAt(int idx) {
        int idx2 = posicioRealLlistaOriginal(mLlistaPersonatgesFiltrada.get(idx));
        mLlistaPersonatges.remove(idx2);
        notifyDataSetChanged();
        mFilter.filter("F");
    }

    public void filtrarLlista(String s) {
        mFilter.setNomProf(s);
        mFilter.filter("F");
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View v = super.getView(position, convertView, parent);

//        final LinearLayout v;
//
//        if (convertView != null) {
//            v = (LinearLayout) convertView;
//        } else {
//            LayoutInflater factory = LayoutInflater.from(this.getContext());
//            v = (LinearLayout) factory.inflate(R.layout.fila_gestio, parent, false);
//        }

        Personatge p = mLlistaPersonatgesFiltrada.get(position);
        RowHolder holder = (RowHolder) v.getTag();
        if (holder == null) {

            holder = new RowHolder();

            holder.imvImatgeGestioFila = (ImageView) v.findViewById(R.id.imvImatgeGestioFila);
            holder.txvNomGestioFila = (TextView) v.findViewById(R.id.txvNomGestioFila);
            holder.txvOficiGestioFila = (TextView) v.findViewById(R.id.txvOficiGestioFila);
            holder.imvAlignmentGestioFila = (ImageView) v.findViewById(R.id.imvAlignmentGestioFila);

            holder.mLlyStrength = (LinearLayout) v.findViewById(R.id.llyStrength);
            holder.mLlyDexterity = (LinearLayout) v.findViewById(R.id.llyDexterity);
            holder.mLlyConstitution = (LinearLayout) v.findViewById(R.id.llyConstitution);
            holder.mLlyIntelligence = (LinearLayout) v.findViewById(R.id.llyIntelligence);
            holder.mLlyWisdom = (LinearLayout) v.findViewById(R.id.llyWisdom);
            holder.mLlyCharisma = (LinearLayout) v.findViewById(R.id.llyCharisma);

            holder.imvSexeGestioFila = (ImageView) v.findViewById(R.id.imvSexeGestioFila);
            holder.imvRasaGestioFila = (ImageView) v.findViewById(R.id.imvRasaGestioFila);

            v.setTag(holder);
        }

        holder.txvNomGestioFila.setText(p.getNom());
        holder.imvImatgeGestioFila.setImageResource(p.getImage());
        holder.txvOficiGestioFila.setText(p.getOfici().toString() + " - (Lvl " + p.calcularNivell() + ")");

        if (p.getRasa().getAlignment().equals(Alignment.ALLIANCE)) {
            holder.imvAlignmentGestioFila.setImageResource(R.drawable.zzz1);
        } else if (p.getRasa().getAlignment().equals(Alignment.HORDE)) {
            holder.imvAlignmentGestioFila.setImageResource(R.drawable.zzz2);
        } else {
            holder.imvAlignmentGestioFila.setImageResource(R.drawable.icono);
        }

        if (p.getSexe().equals(Sexe.MALE)) {
            holder.imvSexeGestioFila.setImageResource(R.drawable.male);
        } else if (p.getSexe().equals(Sexe.FEMALE)) {
            holder.imvSexeGestioFila.setImageResource(R.drawable.female);
        }else{
            holder.imvSexeGestioFila.setImageResource(R.drawable.noimage);
        }

        holder.imvRasaGestioFila.setImageResource(p.getRasa().getImatge());


        carregarAbilitats(p.getStrength(), holder.mLlyStrength);
        carregarAbilitats(p.getDexterity(), holder.mLlyDexterity);
        carregarAbilitats(p.getConstitution(), holder.mLlyConstitution);
        carregarAbilitats(p.getIntelligence(), holder.mLlyIntelligence);
        carregarAbilitats(p.getWisdom(), holder.mLlyWisdom);
        carregarAbilitats(p.getCharisma(), holder.mLlyCharisma);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.animacio_item_fila);
                // v.setAnimation(anim);
                //anim.start();
                Boolean a = checkItem((ListView) parent, position);
                mActivity.habilitarBotons(a);
                Log.i("CCC", "Index" + String.valueOf(position));
            }
        });

        return v;
    }

    public boolean checkItem(ListView listView, int position) {
        boolean isChecked = listView.isItemChecked(position);
        listView.setItemChecked(position, !isChecked);
        index = position;
        return !isChecked;
    }

    public int getIndex() {
        return index;
    }

    public int posicioRealLlistaOriginal(Personatge p) {
        int i;
        for (i = 0; i < mLlistaPersonatges.size(); i++) {
            if (mLlistaPersonatges.get(i).equals(p)) {
                break;
            }
        }
        return i;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private void carregarAbilitats(int NivellAbilitat, LinearLayout l) {

        int Nivell = NivellAbilitat / 8;

        int color;

        if (Nivell == 0 || Nivell < 4) color = mActivity.getResources().getColor(R.color.estat1);
        else if (Nivell == 4 || Nivell < 8)
            color = mActivity.getResources().getColor(R.color.estat2);
        else if (Nivell == 8 || Nivell < 10)
            color = mActivity.getResources().getColor(R.color.estat3);
        else color = mActivity.getResources().getColor(R.color.estat4);

//        int Nivell = NivellAbilitat / 5;
//        int color;
//
//        if (Nivell == 0 || Nivell < 6) color = mActivity.getResources().getColor(R.color.estat1);
//        else if (Nivell == 6 || Nivell < 11)
//            color = mActivity.getResources().getColor(R.color.estat2);
//        else if (Nivell == 11 || Nivell < 16)
//            color = mActivity.getResources().getColor(R.color.estat3);
//        else color = mActivity.getResources().getColor(R.color.estat4);

        if (Nivell == 0) Nivell++;

        l.removeAllViews();

        for (int i = 0; i < Nivell; i++) {
            ImageView im = new ImageView(mActivity);
            im.setImageResource(R.drawable.quadrat_estat);
            im.getDrawable().setColorFilter(color, PorterDuff.Mode.ADD);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, LinearLayout.LayoutParams.MATCH_PARENT);
            //lp.setMargins(0, 0, 0, 0);
            l.addView(im, lp);
        }
    }

    private static class RowHolder {
        ImageView imvImatgeGestioFila;
        TextView txvNomGestioFila;
        TextView txvOficiGestioFila;
        ImageView imvAlignmentGestioFila;

        LinearLayout mLlyStrength;
        LinearLayout mLlyDexterity;
        LinearLayout mLlyConstitution;
        LinearLayout mLlyIntelligence;
        LinearLayout mLlyWisdom;
        LinearLayout mLlyCharisma;

        ImageView imvSexeGestioFila;
        ImageView imvRasaGestioFila;

        public RowHolder() {
        }

    }

    private class ItemFilter extends Filter {

        private String mNomProf;

        public void setNomProf(String s) {
            mNomProf = s;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            final List<Personatge> list = mLlistaPersonatges;
            final ArrayList<Personatge> nlist = new ArrayList<>(list.size());

            if (constraint != null && mNomProf != null) {

                for (Personatge p : list) {
                    if (p.getNom().toLowerCase().contains(mNomProf)) {
                        nlist.add(p);
                    }
                }
                results.values = nlist;
                results.count = nlist.size();
            } else {
                mNomProf = null;
                results.values = mLlistaPersonatges;
                results.count = mLlistaPersonatges.size();

            }
            Log.i("XXX", "FILTRE: " + String.valueOf(results.count));
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mLlistaPersonatgesFiltrada = (ArrayList<Personatge>) results.values;
            notifyDataSetChanged();
            if (results.count == 0) mActivity.habilitarBotons(false);
        }

    }
}
