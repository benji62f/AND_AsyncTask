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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lille1.bl.supercalculator.R;
import com.lille1.bl.supercalculator.prgm.MathFunction;
import com.lille1.bl.supercalculator.prgm.Result;

import java.util.ArrayList;
import java.util.Random;

public class ResultListFragment extends ListFragment {

    private ResultAdapter myAdapter;
    private ArrayList<Result> results;

    public ResultListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_function_list, container, false);
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
        results = ((MainActivity) getActivity()).getResults();

        // Recherche la vue affichant la liste
        ListView listView = this.getListView();

        myAdapter = new ResultAdapter(getContext(), R.layout.fragment_function_list_item, results);
        listView.setAdapter(myAdapter);
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public ResultAdapter getMyAdapter() {
        return myAdapter;
    }

    public void setMyAdapter(ResultAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }
}
