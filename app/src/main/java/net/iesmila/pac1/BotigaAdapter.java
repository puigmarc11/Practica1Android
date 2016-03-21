package net.iesmila.pac1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.iesmila.pac1.model.Personatge;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class BotigaAdapter extends ArrayAdapter<Personatge> {

    private List<Personatge> mLlistaPersonatges;
    private ActivityBotiga mActivity;

    public BotigaAdapter(ActivityBotiga context, List<Personatge> mLlistaPersonatges) {
        super(context, R.layout.fila_botiga, R.id.txvBotigaNom, mLlistaPersonatges);
        mActivity = context;
        this.mLlistaPersonatges = mLlistaPersonatges;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View v = super.getView(position, convertView, parent);

        TextView txvNom = (TextView) v.findViewById(R.id.txvBotigaNom);
        TextView txvRasa = (TextView) v.findViewById(R.id.txvBotigaRasa);
        ImageView imvBotigaImatge = (ImageView) v.findViewById(R.id.imvBotigaImatge);


        Personatge p = mLlistaPersonatges.get(position);

        txvNom.setText(p.getNom());
        txvRasa.setText(p.getRasa().getRasa());
        imvBotigaImatge.setImageResource(p.getImage());


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.animacio_item_fila);
                v.setAnimation(anim);
                anim.start();
                checkItem((ListView) parent, position);
            }
        });

        return v;
    }

    private boolean checkItem(ListView listView, int position) {
        boolean isChecked = listView.isItemChecked(position);
        Log.i("XXX", String.valueOf(position) + !isChecked);
        listView.setItemChecked(position, !isChecked);
        return !isChecked;
    }
}
