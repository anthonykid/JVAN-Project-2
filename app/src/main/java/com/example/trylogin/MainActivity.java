package com.example.trylogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Model.Login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUser, etPass;
    String Username, Password;
    int Role;
    String username = null;
    TextView btn_login,btn_signUp;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etUser = findViewById(R.id.etusername);
        etPass = findViewById(R.id.etpassword);
        btn_login = findViewById(R.id.btn_sign_in);
        btn_signUp = findViewById(R.id.sign_up_txt);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        etUser.setText(username);


        btn_login.setOnClickListener(this);
        buttonEffect(btn_login);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_in:
                Username = etUser.getText().toString();
                Password = etPass.getText().toString();
                login(Username,Password);
                break;
            case R.id.sign_up_txt:
                Intent intent1 = new Intent(this,SignIn.class);
                startActivity(intent1);
                break;
        }
    }

    public static void buttonEffect(View button) {
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

    private void login(String Username, String Password) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call <Login> loginCall = apiInterface.loginResponse(Username,Password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Role = Integer.valueOf(response.body().getData().getRole());
                    intentByRole(Role);
                }else{
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intentByRole(int Role){
        if (Role == 1){
            Intent intent = new Intent(MainActivity.this,MenuAdmin.class);
            intent.putExtra("postRole",Role);
            startActivity(intent);
        }else if(Role == 2){
            Intent intent = new Intent(MainActivity.this, StaffMenu.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(MainActivity.this,MainMenu.class);
            startActivity(intent);
        }
    }
}