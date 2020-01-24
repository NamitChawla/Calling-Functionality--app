package com.example.namit.adddata;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Namit on 22-06-2017.
 */
public class PetsList extends ArrayAdapter<Pets>
{
    private Activity activity;
    private List<Pets> petsList;

    public PetsList(Activity activity,List<Pets> petsList)
    {
        super(activity,R.layout.list_layout,petsList);
        this.activity=activity;
        this.petsList=petsList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = activity.getLayoutInflater();

        View view=inflater.inflate(R.layout.list_layout,null,true);

        TextView petname= (TextView) view.findViewById(R.id.petName);
        TextView petType= (TextView) view.findViewById(R.id.petType);

        Pets pets=petsList.get(position);
        petname.setText(pets.getAnimalName());
        petType.setText(pets.getAnimalType());

        return view;
    }
}
