package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
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

/**
 *
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Start Stetho
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        // End Stetho

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

                //if (CheckLoginValidation())
                if(usernameLoggedIn.equals("admin") && passwordLoggedIn.equals("password")) {
                    Toast.makeText(LoginActivity.this,
                            "Logging Into Admin Account", Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Intent intentAdminActivity1 = new Intent(LoginActivity.this, AdminSettingsActivity.class);
                    //intent.putExtra("USERNAME", usernameLoggedIn);
                    startActivity(intentAdminActivity1);
                }
                else if (CheckLoginValidation()){
                    Toast.makeText(LoginActivity.this,
                            "Welcome to SnapSter", Toast.LENGTH_SHORT).show();

                    String foundUserLoggedInId = Integer.toString(dbHelper.retrieveSelectedUserID(usernameLoggedIn));

                    dbHelper.insertAccountLogData(foundUserLoggedInId, usernameLoggedIn);
                    //dbHelper.insertAccountLogData("0", usernameLoggedIn);

                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Intent intentHomeActitivity = new Intent(LoginActivity.this, HomeActivity.class);
                    //intent.putExtra("USERNAME", usernameLoggedIn);
                    startActivity(intentHomeActitivity);

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

/*        if ((usernameLoggedIn.equals(regUsername)) && (passwordLoggedIn.equals(regPassword))){
            checkResult = true;
        }*/

        return checkResult;
    }
}
