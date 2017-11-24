package com.example.ihp.mobileapplicationprog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Java file is related to 'activity_register.xml'
 * The purpose of this file is to allow user to Register
 *      by providing personal and login information.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText accountNameValue;
    private EditText usernameValue;
    private EditText emailValue;
    private EditText passwordValue;
    private Button btnNewRegister;

    private String usersName;
    private String usersUsername;
    private String userEmail;
    private String userPassword;

    private TextView tvBackClick;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountNameValue = (EditText) findViewById(R.id.etNameReg);
        usernameValue = (EditText) findViewById(R.id.etUsernameReg);
        emailValue = (EditText) findViewById(R.id.etEmailReg);
        passwordValue = (EditText) findViewById(R.id.etPasswordReg);
        btnNewRegister = (Button) findViewById(R.id.btnSignUpReg);
        tvBackClick = (TextView) findViewById(R.id.tvBack);

        // When user click 'Sign Up' button
        btnNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidateRegistrationInputs()){
                    Toast.makeText(RegisterActivity.this,
                            "Registration was Unsuccessful", Toast.LENGTH_SHORT).show();
                }else{

                    //Insert user information into database
                    Contact c = new Contact();
                    c.setGivenName(usersName);
                    c.setUserName(usersUsername);
                    c.setEmailAdress(userEmail);
                    c.setPassword(userPassword);

                    helper.insertContact(c);
                    Toast.makeText(RegisterActivity.this,
                            "Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    intent.putExtra("USERNAME_REG", usersUsername);
//                    intent.putExtra("PASSWORD_REG", userPassword);
                    startActivity(intent);
                }
            }
        });

        tvBackClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignOut = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
            }
        });
    }

    /**
     * This method checks all the text fields for valid input values before allowing the user
     *      to register.
     * @return  Returns 'true' if all text fields have acceptable values, else 'false'
     */
    public boolean ValidateRegistrationInputs(){
        boolean inputValueCheck = true;
        usersName = accountNameValue.getText().toString().trim();
        usersUsername = usernameValue.getText().toString().trim();
        userEmail = emailValue.getText().toString().trim();
        userPassword =  passwordValue.getText().toString().trim();

        if (usersName.isEmpty()){
            accountNameValue.setError("Please Enter a Valid Name");
            accountNameValue.getText().clear();
            inputValueCheck = false;
        }

        if (usersUsername.isEmpty()){
            usernameValue.setError("Please Enter a Username");
            usernameValue.getText().clear();
            inputValueCheck = false;
        }

        if (userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            emailValue.setError("Please Enter a Valid email");
            emailValue.getText().clear();
            inputValueCheck = false;
        }

        if (userPassword.isEmpty()){
            passwordValue.setError("Please Enter a Valid Password");
            passwordValue.getText().clear();
            inputValueCheck = false;
        }

        return inputValueCheck;
    }
}
