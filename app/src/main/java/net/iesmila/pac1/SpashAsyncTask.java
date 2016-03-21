package net.iesmila.pac1;

import android.os.AsyncTask;

public class SpashAsyncTask extends AsyncTask<String, String, String> {

    private ActivityCarrega mActivity;

    public SpashAsyncTask(ActivityCarrega pActivity) {
        mActivity = pActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mActivity.mostrarProgressDescarrega("Loading...");
    }

    @Override
    protected String doInBackground(String... name) {

        String a = "Loading";

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error";
            }

            if (i % 10 == 0) {
                a += ".";
                if (a.endsWith("....")) a = "Loading.";
                publishProgress(a);
            }
        }

        return "OK";
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate(progress);
        if (mActivity != null) mActivity.mostrarProgressDescarrega(progress[0]);
    }

    @Override
    protected void onPostExecute(String name) {
        super.onPostExecute(name);
        if (name == null) {
            mActivity.mostrarErrorDescarrega();
        } else {
            mActivity.fiDescarrega(name);
        }
    }
}
