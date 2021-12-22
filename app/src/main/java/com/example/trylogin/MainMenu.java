package com.example.trylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    LinearLayout fashion,electronic,book,sport,traveling,other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        fashion = findViewById(R.id.fashion_act);
        electronic = findViewById(R.id.electronic_act);
        book = findViewById(R.id.book_act);
        sport = findViewById(R.id.sport_act);
        traveling = findViewById(R.id.traveling_act);
        other = findViewById(R.id.other_act);

        setToButton(fashion);
        setToButton(electronic);
        setToButton(book);
        setToButton(sport);
        setToButton(traveling);
        setToButton(other);
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fashion_act:
                Toast.makeText(MainMenu.this, "fashion", Toast.LENGTH_SHORT).show();
                break;
            case R.id.electronic_act:
                Toast.makeText(MainMenu.this, "electronic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.book_act:
                Toast.makeText(MainMenu.this, "book", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sport_act:
                Toast.makeText(MainMenu.this, "sport", Toast.LENGTH_SHORT).show();
                break;
            case R.id.traveling_act:
                Toast.makeText(MainMenu.this, "traveling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.other_act:
                Toast.makeText(MainMenu.this, "other", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setToButton(View view){
        buttonEffect(view);
        view.setOnClickListener(this);
    }
}