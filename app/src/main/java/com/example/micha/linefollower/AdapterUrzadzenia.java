package com.example.micha.linefollower;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by micha on 27.03.2018.
 */
public class AdapterUrzadzenia extends ArrayAdapter<Urzadzenia> {
    private Activity activity;
    private ArrayList<Urzadzenia> urzadzenia;
    private static LayoutInflater inflater = null;

    public AdapterUrzadzenia (Activity activity, int textViewResourceId, ArrayList<Urzadzenia> urzadzenia) {
        super(activity, textViewResourceId, urzadzenia);
        try {
            this.activity = activity;
            this.urzadzenia = urzadzenia;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return urzadzenia.size();
    }

    public Urzadzenia getItem(Urzadzenia position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;
        public TextView display_number;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.lista_urzadzen, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) vi.findViewById(R.id.nazwaUrzadzenia);
                holder.display_number = (TextView) vi.findViewById(R.id.macUrzadzenia);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_name.setText(urzadzenia.get(position).getNazwa());
            holder.display_number.setText(urzadzenia.get(position).getAdresMac());


        } catch (Exception e) {


        }
        return vi;
    }
}