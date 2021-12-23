package com.example.trylogin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trylogin.API.ApiClient;
import com.example.trylogin.API.ApiInterface;
import com.example.trylogin.Model.Register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    EditText etuserUp, etPassUp,etNameUp;
    TextView btn_signIn,btn_signUp,staff;
    String unameUp,nameUp,passUp;
    String intnetPass, intentUser;
    LinearLayout pindah;
    private int postRole = 0;
    private int role = 0;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        staff = findViewById(R.id.staff);
        pindah = findViewById(R.id.pindahHalaman);
        etNameUp = findViewById(R.id.etnameup);
        etPassUp = findViewById(R.id.etpasswordup);
        etuserUp = findViewById(R.id.etusernameup);
        btn_signUp = findViewById(R.id.btn_sign_up);
        btn_signIn = findViewById(R.id.sign_in_txt);

        Intent intent = getIntent();
        postRole = intent.getIntExtra("postRole",0);

        if (postRole != 0){
            staff.setVisibility(View.VISIBLE);
            pindah.setVisibility(View.INVISIBLE);
            role = 2;
        }else{
            staff.setVisibility(View.INVISIBLE);
            role = 3;
        }

        btn_signUp.setOnClickListener(this);
        buttonEffect(btn_signUp);
        btn_signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_up:
                unameUp = etuserUp.getText().toString();
                nameUp = etNameUp.getText().toString();
                passUp = etPassUp.getText().toString();

                register(unameUp,nameUp,passUp,role);
                break;
            case R.id.sign_in_txt:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void register(String unameUp, String nameUp, String passUp, int role) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerResponse(unameUp,nameUp,role,passUp);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                intentUser = response.body().getData().getUsername();

                if (role == 2){
                    Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent admin = new Intent(SignIn.this, MenuAdmin.class);
                    startActivity(admin);
                }else{
                    Toast.makeText(SignIn.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent user = new Intent(SignIn.this, MainActivity.class);
                    user.putExtra("username",intentUser);
                    startActivity(user);
                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(SignIn.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
}