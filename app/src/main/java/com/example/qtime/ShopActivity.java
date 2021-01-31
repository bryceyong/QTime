package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity {

    private int gold;
    private int rocket;
    private int hat;
    private int goat;
    private int status;

    ImageButton rocketView;
    TextView goldView;
    ImageButton hatView;
    TextView hatPrice;
    TextView rocketPrice;
    ImageButton goatView;
    TextView goatPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Button reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                gold = 260;
                hat = 0;
                goat = 0;
                rocket = 0;
                status = 1;

                saveGoldData();
                saveRocketData();
                saveHatData();
                saveGoatData();
                saveCatData();

                loadGoldData();
                loadRocketData();
                loadHatData();
                loadGoatData();
                loadCatData();

                goldView = (TextView)findViewById(R.id.gold);
                goldView.setText(Integer.toString(gold));
            }
        });




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
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            rocketView.setBackgroundResource(R.drawable.sold);
            rocketPrice.setText("");
        }

        hatView = (ImageButton)findViewById(R.id.hat);
        hatPrice = (TextView)findViewById(R.id.hatPrice);

        if(hat == 0){
            hatView.setBackgroundResource(R.drawable.hat);
            hatView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    if(gold >= 50){
                        gold = gold - 50;
                        saveGoldData();
                        goldView.setText(Integer.toString(gold));
                        hat = 1;
                        saveHatData();
                        hatView.setBackgroundResource(R.drawable.sold);
                        hatPrice.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            hatView.setBackgroundResource(R.drawable.sold);
            hatPrice.setText("");
        }

        goatView = (ImageButton)findViewById(R.id.goat);
        goatPrice = (TextView)findViewById(R.id.goatPrice);

        if(goat == 0){
            goatView.setBackgroundResource(R.drawable.egg);
            goatView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    if(gold >= 200){
                        gold = gold - 200;
                        saveGoldData();
                        goldView.setText(Integer.toString(gold));
                        goat = 1;
                        saveGoatData();
                        goatView.setBackgroundResource(R.drawable.sold);
                        goatPrice.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            goatView.setBackgroundResource(R.drawable.sold);
            goatPrice.setText("");
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
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
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            rocketView.setBackgroundResource(R.drawable.sold);
            rocketPrice.setText("");
        }

        hatView = (ImageButton)findViewById(R.id.hat);
        hatPrice = (TextView)findViewById(R.id.hatPrice);

        if(hat == 0){
            hatView.setBackgroundResource(R.drawable.hat);
            hatView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    if(gold >= 50){
                        gold = gold - 50;
                        saveGoldData();
                        goldView.setText(Integer.toString(gold));
                        hat = 1;
                        saveHatData();
                        hatView.setBackgroundResource(R.drawable.sold);
                        hatPrice.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            hatView.setBackgroundResource(R.drawable.sold);
            hatPrice.setText("");
        }

        goatView = (ImageButton)findViewById(R.id.goat);
        goatPrice = (TextView)findViewById(R.id.goatPrice);

        if(goat == 0){
            goatView.setBackgroundResource(R.drawable.egg);
            goatView.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){
                    if(gold >= 200){
                        gold = gold - 200;
                        saveGoldData();
                        goldView.setText(Integer.toString(gold));
                        goat = 1;
                        saveGoatData();
                        goatView.setBackgroundResource(R.drawable.sold);
                        goatPrice.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Not enough gold", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            goatView.setBackgroundResource(R.drawable.sold);
            goatPrice.setText("");
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
        gold = sharedPreferences.getInt("gold", 260);
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

    public void saveHatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("hat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("hat", hat);
        editor.apply();

    }

    public void loadHatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("hat", MODE_PRIVATE);
        hat = sharedPreferences.getInt("hat", 0);
    }

    public void saveGoatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("goat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("goat", goat);
        editor.apply();

    }

    public void loadGoatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("goat", MODE_PRIVATE);
        goat = sharedPreferences.getInt("goat", 0);
    }

    public void saveCatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cat", status);
        editor.apply();

    }

    public void loadCatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        status = sharedPreferences.getInt("cat", 1);

    }
}