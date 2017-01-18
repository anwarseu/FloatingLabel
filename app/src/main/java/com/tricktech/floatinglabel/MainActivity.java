package com.tricktech.floatinglabel;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String BD_PHONE_NUMBER_REEXP ="^(?:\\+?88)?01[15-9]\\d{8}$";
    public TextInputLayout input_layout_name,input_layout_email,input_layout_phone,
            input_layout_password;
    public EditText input_name,input_email,input_phone,input_password;
    public Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_phone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_password);

        input_name = (EditText) findViewById(R.id.input_name);
        input_email = (EditText) findViewById(R.id.input_email);
        input_phone = (EditText) findViewById(R.id.input_phone);
        input_password = (EditText) findViewById(R.id.input_password);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
              submitForm();
    }

    public void submitForm(){
         if (!validateFullname()){
             return;
         }

        if (!validateEmail()){
            return;
        }

        if (!validatePhone()){
            return;
        }
        if (!validatePassword()){
            return;
        }

        Toast.makeText(MainActivity.this, "Thank you!", Toast.LENGTH_LONG).show();
    }

    private boolean validateFullname(){
        String name = input_name.getText().toString().trim();
        if (name.isEmpty()){
             input_layout_name.setError(getString(R.string.msg_err_name));
            requestFocus(input_name);
            return false;
        }else {
            input_layout_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail(){
        String email = input_email.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)){
            input_layout_email.setError(getString(R.string.msg_err_email));
            requestFocus(input_email);
            return false;
        }else {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone(){
        String phone = input_phone.getText().toString().trim();
        if (phone.isEmpty() || !isValidPhone(phone)){
            input_layout_phone.setError(getString(R.string.msg_err_phone));
            requestFocus(input_phone);
            return false;
        }else {
            input_layout_phone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword(){
        String password = input_password.getText().toString().trim();
        if (password.isEmpty() || password.length() > 6){
            input_layout_password.setError(getString(R.string.msg_err_password));
            requestFocus(input_password);
            return false;
        }else {
            input_layout_password.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPhone(String phone){
        Pattern mPattern = Pattern.compile(BD_PHONE_NUMBER_REEXP);
        return mPattern.matcher(phone).matches();
    }

    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
