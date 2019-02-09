package com.dev.vova.ardesign7;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class requestForLogin extends AppCompatActivity implements View.OnClickListener {

    Button sendEmail ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_request_for_login );


        TextView textarea = (TextView) findViewById( R.id.tv );  // tv is id in XML file for TextView
        textarea.setTextSize( 20 );
        textarea.setTextColor( Color.rgb( 0, 0xff, 0 ) );
        textarea.setTypeface( Typeface.SERIF, Typeface.ITALIC );

        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );

        sendEmail=findViewById( R.id.sendEmailBtn ) ;
        sendEmail.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {

    }
}
