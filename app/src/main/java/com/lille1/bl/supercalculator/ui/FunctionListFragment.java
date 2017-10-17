package com.lille1.bl.supercalculator.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lille1.bl.supercalculator.R;
import com.lille1.bl.supercalculator.prgm.MathFunction;

import java.util.ArrayList;
import java.util.Random;

public class FunctionListFragment extends ListFragment {

    private FunctionAdapter myAdapter;
    private ArrayList<MathFunction> functions;

    public FunctionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_function_list, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_action_history:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new ResultListFragment()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        functions = ((MainActivity) getActivity()).getFunctions();

        // Recherche la vue affichant la liste
        ListView listView = this.getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l){
                MathFunction selected = functions.get(position);
                showParamsPopup(selected);
            }
        });

        for(int i=1 ; i<=10 ; i++)
            functions.add(new MathFunction("function " + i, new Random().nextInt(3)+1, (MainActivity) this.getActivity()));

        myAdapter = new FunctionAdapter(getContext(), R.layout.fragment_function_list_item, functions);
        listView.setAdapter(myAdapter);
    }

    /**
     * Déclenche la boîte de dialogue "Rechercher", permettant de rechercher un livre par mot clé.
     */
    private void showParamsPopup(final MathFunction selected){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = (getActivity()).getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setTitle(getString(R.string.add_params));
        builder.setIcon(android.R.drawable.ic_menu_preferences);

        final LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        layout.setGravity(Gravity.CLIP_VERTICAL);
        layout.setPadding(2, 2, 2, 2);

        for(int i=0 ; i<selected.getNbParams() ; i++) {
            LinearLayout innerLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);
            innerLayout.setLayoutParams(params);

            TextView tv = new TextView(getContext());
            tv.setText("Param " + (i+1));
            tv.setPadding(40, 40, 40, 40);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(20);

            EditText et = new EditText(getContext());
            et.setLayoutParams(params);
            et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            et.setId(i);

            innerLayout.addView(tv);
            innerLayout.addView(et);
            layout.addView(innerLayout);
        }

        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("Execute", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                double[] params = new double[selected.getNbParams()];
                                for(int i=0 ; i<selected.getNbParams() ; i++){
                                    String value = ((EditText) layout.findViewById(i)).getText().toString();
                                    if(!"".equals(value))
                                        params[i] = Double.parseDouble(value);
                                    else
                                        params[i] = 0;
                                }
                                selected.execute(params);
                            }
                        }
                );
        builder.create();
        builder.show();
    }
}
