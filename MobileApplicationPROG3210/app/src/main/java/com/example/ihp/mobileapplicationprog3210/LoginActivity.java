package com.example.ihp.mobileapplicationprog3210;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This Java file is related to 'activity_login.xml'
 * The purpose of this file is to allow users to login to the app.
 *      Main landing activity.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText usernameLogin;

    private EditText passwordLogin;

    private Button btnSignIn;

    private TextView etSignUp;

    private String usernameLoggedIn = null;

    private String passwordLoggedIn = null;

    private String regUsername = null;

    private String regPassword = null;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Code used to app database information
        // Start Stetho  -> chrome://inspect/#devices
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        // End Stetho

        mAuth = FirebaseAuth.getInstance();

        usernameLogin = (EditText) findViewById(R.id.etUsernameMain);

        passwordLogin = (EditText) findViewById(R.id.etPasswordMain);

        btnSignIn = (Button) findViewById(R.id.btnSignInMain);

        etSignUp = (TextView) findViewById(R.id.etSignUpMain);

        regUsername = getIntent().getStringExtra("USERNAME_REG");

        regPassword = getIntent().getStringExtra("PASSWORD_REG");

        // When user clicks 'Sign In' button
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                usernameLoggedIn = usernameLogin.getText().toString().trim();
                passwordLoggedIn = passwordLogin.getText().toString().trim();

                if(usernameLoggedIn.contains("admin")) {

                    //Firebase User connection components
                    mAuth.signInWithEmailAndPassword(usernameLoggedIn, passwordLoggedIn).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //finish();
                                Toast.makeText(LoginActivity.this, "Successfully Logged Into Admin Account", Toast.LENGTH_SHORT).show();
                                //Visual Authentication progress bar
                                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("Authenticating...");
                                progressDialog.show();

                                Intent intentAdminActivity = new Intent(LoginActivity.this, AdminSettingsActivity.class);

                                startActivity(intentAdminActivity);


                            }else{
                                Toast.makeText(LoginActivity.this, "Un-Successful LogIn to Admin Account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else if (CheckLoginValidation()){

                    Toast.makeText(LoginActivity.this,
                            "Welcome to SnapSter: " + usernameLoggedIn, Toast.LENGTH_SHORT).show();

                    //Visual Authentication progress bar
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    String foundUserLoggedInId = Integer.toString(dbHelper.retrieveSelectedUserID(usernameLoggedIn));

                    dbHelper.insertAccountLogData(foundUserLoggedInId, usernameLoggedIn);

                    Intent intentHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);

                    intentHomeActivity.putExtra("LOGGED_IN_USER_ID", foundUserLoggedInId);

                    startActivity(intentHomeActivity);

                }else{

                    Toast.makeText(LoginActivity.this,
                            "Please Enter Correct Username and Password", Toast.LENGTH_SHORT).show();

                    usernameLogin.getText().clear();

                    passwordLogin.getText().clear();
                }
            }
        });

        // When user clicks 'Sign Up' button
        etSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });

    }

    /**
     * This method checks if 'Sign In' 'Username' and 'Password' match registered 'Username' and
     *      'Password'
     * @return  'True' if 'Username' and 'Password' match with user registered information within
     *              'Sign Up', else 'False'
     */
    public boolean CheckLoginValidation()
    {
        boolean checkResult = false;
        String searchedPassword = null;

        if (usernameLoggedIn.equals("") || passwordLoggedIn.equals("")){

            checkResult = false;

        }
        else {

            searchedPassword = dbHelper.searchUserNameInDatabase(usernameLoggedIn);
        }

        if (passwordLoggedIn.equals(searchedPassword)){

            checkResult = true;

        }


        return checkResult;

    }
}
