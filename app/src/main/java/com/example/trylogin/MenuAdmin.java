package com.example.trylogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MenuAdmin extends AppCompatActivity implements View.OnClickListener{

    LinearLayout staff,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        staff = findViewById(R.id.addStaff);
        stock = findViewById(R.id.addstock);

        setToButton(staff);
        setToButton(stock);

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
            case R.id.addStaff:
                Intent intent = new Intent(MenuAdmin.this, SignIn.class);
                intent.putExtra("postRole",2);
                startActivity(intent);
                break;
            case R.id.addstock:
                Intent intent1 = new Intent(MenuAdmin.this, StaffMenu.class);
                startActivity(intent1);
                break;
        }
    }

    public void setToButton(View view){
        buttonEffect(view);
        view.setOnClickListener(this);
    }
}