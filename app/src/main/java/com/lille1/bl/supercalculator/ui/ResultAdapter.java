package com.lille1.bl.supercalculator.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lille1.bl.supercalculator.R;
import com.lille1.bl.supercalculator.prgm.Result;

import java.util.ArrayList;

/**
 * Created by Benjamin on 08/12/2016.
 */

public class ResultAdapter extends ArrayAdapter<Result> {

    private Context context;
    private ArrayList<Result> results;

    public ResultAdapter(Context context, int resource, ArrayList<Result> objects) {
        super(context, resource, objects);
        this.context = context;
        this.results = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_function_list_item, parent, false);

        final Result item = getItem(position);
        ((TextView) view.findViewById(R.id.function_item_name)).setText(item.getFunctionName());

        if(item.getParams().length == 0){
            TextView tv = (TextView) view.findViewById(R.id.function_item_params);
            tv.setText("No param");
        } else {
            TextView tv = (TextView) view.findViewById(R.id.function_item_params);
            tv.setText("Param(s): ");
            for (int i = 0; i < item.getParams().length; i++) {
                tv.setText(tv.getText().toString() + item.getParams()[i]);
                if (i != item.getParams().length - 1)
                    tv.setText(tv.getText().toString() + ", ");
            }
            tv.setText(tv.getText().toString() + " - Result: " + item.getResult());
        }
        ((ImageView) view.findViewById(R.id.function_item_image)).setImageDrawable(context.getResources().getDrawable(R.drawable.history_icon));

        ((ImageView) view.findViewById(R.id.function_item_arrow)).setImageDrawable(null);

        return view;
    }
}
