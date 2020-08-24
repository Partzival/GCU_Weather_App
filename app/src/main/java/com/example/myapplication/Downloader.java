package com.example.myapplication;
/*
RYAN DUFFY - S1826064
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


public class Downloader extends AsyncTask<Void, Void, Object> {

    private Context c;
    private String urlAddress;
    private RecyclerView rv;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, RecyclerView rv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Fetch Headline");
        pd.setMessage("Fetching.....Please wait");
        pd.show();
    }

    @Override
    protected Object doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);

        pd.dismiss();
        if (data.toString().startsWith("Error")) {
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show();
        } else {
            //CALL PARSER
            new RSSParser(c, (InputStream) data, rv).execute();
        }
    }

    private Object downloadData() {
        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }

        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            InputStream is = new BufferedInputStream(con.getInputStream());

            return is;

        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_EROR;
        }
    }
}

