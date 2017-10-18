package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Java file is related to 'activity_main.xml'
 * The purpose of this file is to allow user to login
 *      to the app. If user is not register within the
 *      app, user must 'Sign Up' before logging in within
 *      the app. User logs in with 'Username' and 'Password'
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

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                if (CheckLoginValidation()){
                    Toast.makeText(LoginActivity.this,
                            "Welcome to SnapSter", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    //intent.putExtra("USERNAME", usernameLoggedIn);
                    startActivity(intent);

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
            searchedPassword = helper.searchUserNameInDatabase(usernameLoggedIn);
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
