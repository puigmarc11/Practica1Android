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

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        //View v = super.getView(position, convertView, parent);

        final LinearLayout v;

        if (convertView != null) {
            v = (LinearLayout) convertView;
        } else {
            LayoutInflater factory = LayoutInflater.from(this.getContext());
            v = (LinearLayout) factory.inflate(R.layout.fila_gestio, parent, false);
        }

        Personatge p = mLlistaPersonatgesFiltrada.get(position);
        RowHolder holder = (RowHolder) v.getTag();
        if (holder == null) {

            holder = new RowHolder();

            holder.imvImatgeGestioFila = (ImageView) v.findViewById(R.id.imvImatgeGestioFila);
            holder.txvNomGestioFila = (TextView) v.findViewById(R.id.txvNomGestioFila);
            holder.imvAlignmentGestioFila = (ImageView) v.findViewById(R.id.imvAlignmentGestioFila);

            holder.mLlyStrength = (LinearLayout) v.findViewById(R.id.llyStrength);
            holder.mLlyDexterity = (LinearLayout) v.findViewById(R.id.llyDexterity);
            holder.mLlyConstitution = (LinearLayout) v.findViewById(R.id.llyConstitution);
            holder.mLlyIntelligence = (LinearLayout) v.findViewById(R.id.llyIntelligence);
            holder.mLlyWisdom = (LinearLayout) v.findViewById(R.id.llyWisdom);
            holder.mLlyCharisma = (LinearLayout) v.findViewById(R.id.llyCharisma);

            holder.imvSexeGestioFila = (ImageView) v.findViewById(R.id.imvSexeGestioFila);

            v.setTag(holder);
        }


        holder.txvNomGestioFila.setText(p.getNom());
        holder.imvImatgeGestioFila.setImageResource(p.getImage());

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
        }

        carregarAbilitats(p.getStrength(), holder.mLlyStrength);
        carregarAbilitats(p.getDexterity(), holder.mLlyDexterity);
        carregarAbilitats(p.getConstitution(), holder.mLlyConstitution);
        carregarAbilitats(p.getIntelligence(), holder.mLlyIntelligence);
        carregarAbilitats(p.getWisdom(), holder.mLlyWisdom);
        carregarAbilitats(p.getCharisma(), holder.mLlyCharisma);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.animacio_item_fila);
               // v.setAnimation(anim);
                //anim.start();
                Boolean a = checkItem((ListView) parent, position);
                mActivity.habilitarBotons(a);
                index = position;
            }
        });

        return v;
    }

    private boolean checkItem(ListView listView, int position) {
        boolean isChecked = listView.isItemChecked(position);
        listView.setItemChecked(position, !isChecked);
        return !isChecked;
    }

    public int getIndex() {
        return index;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private void carregarAbilitats(int NivellAbilitat, LinearLayout l) {

        int Nivell = NivellAbilitat / 9;

        int color;

        if (Nivell == 0 || Nivell < 3) color = mActivity.getResources().getColor(R.color.estat1);
        else if (Nivell == 3 || Nivell < 6)
            color = mActivity.getResources().getColor(R.color.estat2);
        else if (Nivell == 6 || Nivell < 8)
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
            lp.setMargins(2, 0, 0, 0);
            l.addView(im, lp);
        }
    }

    private static class RowHolder {
        ImageView imvImatgeGestioFila;
        TextView txvNomGestioFila;
        ImageView imvAlignmentGestioFila;

        LinearLayout mLlyStrength;
        LinearLayout mLlyDexterity;
        LinearLayout mLlyConstitution;
        LinearLayout mLlyIntelligence;
        LinearLayout mLlyWisdom;
        LinearLayout mLlyCharisma;

        ImageView imvSexeGestioFila;

        public RowHolder() {
        }

    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            final List<Personatge> list = mLlistaPersonatges;
            final ArrayList<Personatge> nlist = new ArrayList<>(list.size());

            if (constraint != null) {

                for (Personatge p : list) {
                    if (p.getNom().toLowerCase().contains(constraint)) {
                        nlist.add(p);
                    }
                }
                results.values = nlist;
                results.count = nlist.size();

                Log.i("ZZZ", String.valueOf(nlist.size()));

            } else {
                results.values = mLlistaPersonatges;
                results.count = mLlistaPersonatges.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mLlistaPersonatgesFiltrada = (ArrayList<Personatge>) results.values;

            notifyDataSetChanged();
        }

    }
}
