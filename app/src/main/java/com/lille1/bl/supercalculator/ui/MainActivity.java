package com.lille1.bl.supercalculator.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lille1.bl.supercalculator.R;
import com.lille1.bl.supercalculator.prgm.MathFunction;
import com.lille1.bl.supercalculator.prgm.Result;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MathFunction> functions = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new FunctionListFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public ArrayList<MathFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<MathFunction> functions) {
        this.functions = functions;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
