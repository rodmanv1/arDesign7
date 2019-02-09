package com.dev.vova.ardesign7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    Button requestLoginBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        loginBtn = findViewById( R.id.loginBtn );
        loginBtn.setOnClickListener( this );


        requestLoginBtn = findViewById( R.id.requestLoginBtn );
        requestLoginBtn.setOnClickListener( this );


    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.loginBtn:
                intent = new Intent( this, requestForLogin.class );
                startActivity( intent );

                break;

            case R.id.requestLoginBtn:
                intent = new Intent( this, requestForLogin.class );
                startActivity( intent );

                break;


        }


    }
}