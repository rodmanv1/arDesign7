package com.dev.vova.ardesign7;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    Button registerBtn;
    EditText username;
    EditText password;

    String LOG_TAG = "myLogs";

    // Toolbar toolbar = findViewById( R.id.toolbar );

    //  @Override
//    public void setSupportActionBar(@Nullable android.support.v7.widget.Toolbar toolbar) {
//        super.setSupportActionBar( toolbar );
//
//        getSupportActionBar().setTitle( "Ar Design" );
//        getSupportActionBar().setIcon( getDrawable(R.drawable.ardesign_icon ) );
//    }

    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        username = findViewById( R.id.username );
        password = findViewById( R.id.password );


        loginBtn = findViewById( R.id.loginBtn );
        loginBtn.setOnClickListener( this );


        registerBtn = findViewById( R.id.registerBtn );
        registerBtn.setOnClickListener( this );

        FirebaseApp.initializeApp( this );

        mAuth = FirebaseAuth.getInstance();

        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN );


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI( currentUser );
    }

    private void updateUI(FirebaseUser currentUser) {

    }

    public void signIn(String username, String password) {

        mAuth.signInWithEmailAndPassword( username, password )
                .addOnCompleteListener( this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( LOG_TAG, "signInWithEmail:success" );
                            //      Toast.makeText( MainActivity.this, "Login Success", Toast.LENGTH_SHORT ).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI( user );
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // Name, email address, and profile photo Url
                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                //Uri photoUrl = user.getPhotoUrl();

                                // Check if user's email is verified
                                boolean emailVerified = user.isEmailVerified();

                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getIdToken() instead.
                                String uid = user.getUid();
                                Intent intent = new Intent( MainActivity.this, Navigation_Sidebar.class );
                                startActivity( intent );
                                finish();
                                Toast.makeText( MainActivity.this, "Successful Login,the uid is=" + uid, Toast.LENGTH_LONG ).show();

                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( LOG_TAG, "signInWithEmail:failure", task.getException() );
                            Toast.makeText( MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT ).show();
                            updateUI( null );
                        }

                        // ...
                    }
                } );


    }


    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.loginBtn:


                signIn( username.getText().toString(), password.getText().toString() );


                break;

            case R.id.registerBtn:
                register( username.getText().toString(), password.getText().toString() );


                break;


        }


    }

    public void register(String username, String password) {

        mAuth.createUserWithEmailAndPassword( username, password )
                .addOnCompleteListener( this, new OnCompleteListener <AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task <AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d( LOG_TAG, "createUserWithEmail:success" );
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI( user );


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w( LOG_TAG, "createUserWithEmail:failure", task.getException() );
                            Toast.makeText( MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT ).show();
                            updateUI( null );
                        }

                        // ...
                    }
                } );


    }
}