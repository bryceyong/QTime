package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    private int gold;
    private int rocket;

    ImageButton rocketView;
    TextView goldView;
    TextView rocketPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        loadGoldData();
        loadRocketData();

        goldView = (TextView)findViewById(R.id.gold);
        goldView.setText(Integer.toString(gold));

        rocketView = (ImageButton)findViewById(R.id.rocket);
        rocketPrice = (TextView)findViewById(R.id.rocketPrice);

        if(rocket == 0){
            rocketView.setBackgroundResource(R.drawable.rocket);
            rocketView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    if(gold >= 10){
                        gold = gold - 10;
                        saveGoldData();
                        goldView.setText(Integer.toString(gold));
                        rocket = 1;
                        saveRocketData();
                        rocketView.setBackgroundResource(R.drawable.sold);
                        rocketPrice.setText("");
                    }
                }
            });
        } else {
            rocketView.setBackgroundResource(R.drawable.sold);
            rocketPrice.setText("");
        }
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
        gold = sharedPreferences.getInt("gold", 10);
    }

    public void saveRocketData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("rocket", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("rocket", rocket);
        editor.apply();

    }

    public void loadRocketData(){
        SharedPreferences sharedPreferences = getSharedPreferences("rocket", MODE_PRIVATE);
        rocket = sharedPreferences.getInt("rocket", 0);
    }
}