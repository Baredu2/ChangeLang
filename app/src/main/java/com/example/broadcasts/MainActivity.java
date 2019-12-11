package com.example.broadcasts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import java.util.Locale;

import android.app.ActionBar;
import android.app.LauncherActivity;

import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.view.MenuItem;
import android.widget.ListView;
import android.app.ActionBar;


public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        androidx.appcompat.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(getResources().getString(R.string.app_name));


        Button changeMyLang = findViewById(R.id.changeMyLang);
        changeMyLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                showChangeLanguageDialog();
            }
        });
    }



    private void showChangeLanguageDialog() {
        final String[] listItems = {"Polski", "Italian", "English"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle(("Choose language"));
        mbuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale("pl");
                    recreate();
                } else if (i == 1) {
                    setLocale("it");
                    recreate();
                } else if (i == 2) {
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mbuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}