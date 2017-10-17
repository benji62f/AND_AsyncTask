package com.lille1.bl.supercalculator.prgm;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.lille1.bl.supercalculator.ui.MainActivity;

/**
 * Created by Benjamin on 16/10/2017.
 */

public class MathFunction {
    private String name;
    private int nbParams;

    private MainActivity activity;

    public MathFunction(String name, int nbParams, MainActivity activity){
        this.name = name;
        this.nbParams = nbParams;
        this.activity = activity;
    }

    public void execute(double[] params){
        /**
         * AsyncTask
         */
        new ComplexeCalculation().execute(params);

        /**
         * Sequential code
         */
        //makeAVeryComplexeCalculation(params);
    }

    /**
     * Simulates a long calculation
     */
    private void makeAVeryComplexeCalculation(double[] params){
        try {
            for(int i=1 ; i<=10 ; i++) { // waits 10 seconds
                synchronized (this) {
                    wait(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double result = Math.random()*100+1;
        activity.getResults().add(new Result(getName(), params, result)); // records the result in the result list
    }

    private class ComplexeCalculation extends AsyncTask<double[], Integer, Double> {
        private double[] params;
        private int id;

        /**
         * These attributes are used for the notification.
         */
        private NotificationCompat.Builder mBuilder;
        private NotificationManager mNotifyManager;

        /**
         * This is the main method of the AsyncTask. This is used to simulate a hard calculation.
         * @param params Calculation params
         * @return Calculation result
         */
        protected Double doInBackground(double[]... params) {
            this.params = params[0]; // retrieves params
            try { // waits 10 seconds
                for(int i=1 ; i<=10 ; i++) {
                    synchronized (this) {
                        wait(1000);
                    }
                    publishProgress(i*10); // calls onProgressUpdate
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Math.random()*100+1; // returns calculation result
        }

        /**
         * This method is called before the AsyncTask doInBackground method.
         * This is used to create the notification progress bar.
         */
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

        /**
         * This method is called during the AsyncTask doInBackground method.
         * This is used to update the notification progress value.
         */
        protected void onProgressUpdate(Integer... progress) {
            mBuilder.setProgress(100, progress[0], false)
                    .setContentText(name + ": " + progress[0] + " %");
            mNotifyManager.notify(id, mBuilder.build());
        }

        /**
         * This method is called after the AsyncTask doInBackground method.
         * This is used to remove the notification progress bar.
         */
        protected void onPostExecute(Double result) {
            mBuilder.setContentText("Calculation complete")
                    // Removes the progress bar
                    .setProgress(0, 0, false);
            mNotifyManager.notify(id, mBuilder.build());

            activity.getResults().add(new Result(getName(), this.params, result)); // records the result in the result list
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
