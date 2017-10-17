package com.lille1.bl.supercalculator.prgm;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.lille1.bl.supercalculator.ui.MainActivity;

import java.util.Calendar;

/**
 * Created by Benjamin on 16/10/2017.
 */

public class MathFunction {
    private String name;
    private int nbParams;

    /**
     * These attributes are used for the notification.
     */
    private MainActivity activity;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;

    public MathFunction(String name, int nbParams, MainActivity activity){
        this.name = name;
        this.nbParams = nbParams;
        this.activity = activity;
    }

    public double execute(double[] params){
        new ComplexeCalculation().execute(params);

        return 1.0;
        //return makeAVeryComplexeCalculation();
    }

    /**
     * Simulates a long calculation
     */
    private double makeAVeryComplexeCalculation(){
        try {
            for(int i=1 ; i<=10 ; i++) { // waits 10 seconds
                synchronized (this) {
                    wait(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Math.random()*100+1;
    }

    private class ComplexeCalculation extends AsyncTask<double[], Integer, Double> {
        private double[] params;
        private int id;

        protected Double doInBackground(double[]... params) {
            this.params = params[0];
            try {
                for(int i=1 ; i<=10 ; i++) {
                    synchronized (this) {
                        wait(1000);
                    }
                    publishProgress(i*10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Math.random()*100+1;
        }

        protected void onPreExecute(){
            mNotifyManager =
                    (NotificationManager) activity.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new NotificationCompat.Builder(activity.getApplicationContext());
            mBuilder.setContentTitle("SuperCalculator")
                    .setContentText("Calculation in progress")
                    .setSmallIcon(android.R.drawable.ic_media_play);
            // Displays the progress bar for the first time.
            id = View.generateViewId();
            mNotifyManager.notify(id, mBuilder.build());
        }

        protected void onProgressUpdate(Integer... progress) {
            mBuilder.setProgress(100, progress[0], false)
                    .setContentText(name + ": " + progress[0] + " %");
            mNotifyManager.notify(id, mBuilder.build());
        }

        protected void onPostExecute(Double result) {
            mBuilder.setContentText("Calculation complete")
                    // Removes the progress bar
                    .setProgress(0, 0, false);
            mNotifyManager.notify(id, mBuilder.build());

            activity.getResults().add(new Result(getName(), this.params, result));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbParams() {
        return nbParams;
    }

    public void setNbParams(int nbParams) {
        this.nbParams = nbParams;
    }
}
