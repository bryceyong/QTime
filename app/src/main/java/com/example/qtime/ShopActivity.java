package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class ShopActivity extends AppCompatActivity {

    private int gold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    public void saveGoldData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("gold", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("gold", gold);
        editor.apply();

    }

    public void loadGoldData(){
        SharedPreferences sharedPreferences = getSharedPreferences("gold", MODE_PRIVATE);
        gold = sharedPreferences.getInt("gold", 0);
    }
}