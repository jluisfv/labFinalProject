package com.example.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ArrayCustomAdapter extends ArrayAdapter {
    private final Activity activity;
    private final String[] names;

    public ArrayCustomAdapter(Activity activity, String[] names)
    {
        super(activity, R.layout.rowhistoriallayout,names);
        this.activity = activity;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView;

        if(rowView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.rowhistoriallayout,parent,false);
        }

        //se enlazan los componetes del array
        TextView tvsolicitud;

        tvsolicitud = rowView.findViewById(R.id.tvSolicitud);
        //se setean los componentes de dicho array
        String departamento = names[position];
        tvsolicitud.setText(departamento);

        return  rowView;
    }

}
