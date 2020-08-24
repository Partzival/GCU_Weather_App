package com.example.myapplication;
/*
RYAN DUFFY - S1826064
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    private long backPressedTime;
    private MenuAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final ArrayList<com.example.myapplication.MenuItem> menuItems = new ArrayList<>();
    public static final String EXTRA_NUMBER = "com.example.new_activity.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //uses sharedpreferences to make the dialog box only appear on
        //first opening of the app
        SharedPreferences sPrefs = getSharedPreferences("sPrefs", MODE_PRIVATE);
        boolean firstStart = sPrefs.getBoolean("firstStart", true);
        if (firstStart) {
            showStartDialog();
        }

        createMenuList();
        buildRecyclerView();

        //Attempt to launch an activity outside our app using the fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchWebsite();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onItemClick(int position) {
        Log.d("MYTAG", "onItemClick: clicked");
        Log.d("MYTAG", "" + position);

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_NUMBER, position);
        startActivity(intent);
    }

    public void createMenuList() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "Glasgow", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "Manchester", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "London", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "New York", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "Oman", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "Mauritius", "" + currentDate));
        menuItems.add(new com.example.myapplication.MenuItem(R.drawable.ic_update_black_24dp, "Bangladesh", "" + currentDate));
        mAdapter = new MenuAdapter(menuItems, this);
    }

    public void buildRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public void launchWebsite() {
        String bbcWeather = "https://www.bbc.co.uk/weather";
        Uri webAddress = Uri.parse(bbcWeather);

        Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webAddress);
        if (goToGoogle.resolveActivity(getPackageManager()) != null) {
            startActivity(goToGoogle);
        }
    }

    public void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Welcome to my app!")
                .setMessage("Select a City to see a 3 day forecast")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences sPreds = getSharedPreferences("sPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreds.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}




