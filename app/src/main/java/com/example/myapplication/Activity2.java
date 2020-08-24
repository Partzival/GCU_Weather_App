package com.example.myapplication;
/*
RYAN DUFFY - S1826064
 */
import android.content.ClipData;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class Activity2 extends AppCompatActivity {

    private String manchesterURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";
    private String glasgowURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579";
    private String londonURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643743";
    private String newYorkURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/5128581";
    private String omanURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/287286";
    private String mauritiusURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/934154";
    private String bangladeshURLSource = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241";

    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.rv_Description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        int ID = getIntent().getIntExtra(MainActivity.EXTRA_NUMBER, -1);

        switch (ID) {
            case 0: //glasgow
                new Downloader(Activity2.this, glasgowURLSource, recyclerView).execute();
                break;
            case 1: //manchester
                new Downloader(Activity2.this, manchesterURLSource, recyclerView).execute();
                break;
            case 2: //london
                new Downloader(Activity2.this, londonURLSource, recyclerView).execute();
                break;
            case 3: //new york
                new Downloader(Activity2.this, newYorkURLSource, recyclerView).execute();
                break;
            case 4: //oman
                new Downloader(Activity2.this, omanURLSource, recyclerView).execute();
                break;
            case 5: //mauritius
                new Downloader(Activity2.this, mauritiusURLSource, recyclerView).execute();
                break;
            case 6: //bangladesh
                new Downloader(Activity2.this, bangladeshURLSource, recyclerView).execute();
                break;
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Downloader(Activity2.this, manchesterURLSource, recyclerView).execute();
            }
        });
    }

}
