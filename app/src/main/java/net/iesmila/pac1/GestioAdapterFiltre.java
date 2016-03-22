package net.iesmila.pac1;

import android.inputmethodservice.Keyboard;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 22/03/2016.
 */

public class GestioAdapterFiltre extends RecyclerView.Adapter<GestioAdapterFiltre.ViewHolder> {

    private ActivityGestio mActivity;
    private List<Integer> llista;
    private int index;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mTextView;

        public ViewHolder(View v) {
            super(v);
            ImageView text = (ImageView) v.findViewById(R.id.txvFiltreRasa);
            mTextView = text;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GestioAdapterFiltre(ActivityGestio a, List<Integer> myDataset) {
        mActivity = a;
        llista = myDataset;
    }

    @Override
    public GestioAdapterFiltre.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_filtre, parent, false);



        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setImageResource(llista.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return llista.size();
    }

}
