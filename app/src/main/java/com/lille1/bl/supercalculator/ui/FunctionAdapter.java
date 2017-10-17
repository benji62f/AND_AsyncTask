package com.lille1.bl.supercalculator.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lille1.bl.supercalculator.R;
import com.lille1.bl.supercalculator.prgm.MathFunction;

import java.util.ArrayList;

/**
 * Created by Benjamin on 08/12/2016.
 */

public class FunctionAdapter extends ArrayAdapter<MathFunction> {

    private Context context;
    private ArrayList<MathFunction> functions;

    public FunctionAdapter(Context context, int resource, ArrayList<MathFunction> objects) {
        super(context, resource, objects);
        this.context = context;
        this.functions = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_function_list_item, parent, false);

        final MathFunction item = getItem(position);
        ((TextView) view.findViewById(R.id.function_item_name)).setText(item.getName());

        if(item.getNbParams() < 2)
            ((TextView) view.findViewById(R.id.function_item_params)).setText(item.getNbParams() + " " + context.getResources().getString(R.string.param_required));
        else
            ((TextView) view.findViewById(R.id.function_item_params)).setText(item.getNbParams() + " " + context.getResources().getString(R.string.params_required));

        ((ImageView) view.findViewById(R.id.function_item_image)).setImageDrawable(context.getResources().getDrawable(R.drawable.function_icon));

        return view;
    }
}
