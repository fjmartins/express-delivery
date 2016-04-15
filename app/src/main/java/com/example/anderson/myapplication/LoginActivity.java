package com.example.anderson.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void logar(View view){
        //Intent intent = new Intent(this, PrincipalActivity.class);
        //startActivity(intent);
    }

    public void visitar(View view){
        //Intent intent = new Intent(this, PrincipalActivity.class);
        //startActivity(intent);
    }

}
