package com.colorwalletalert.ui;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.colorwalletalert.ui.R;
import com.colorwalletalert.utils.PreferencesManagerUtil;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

public class CWALoginActivity extends AppCompatActivity {

    private final String LOGIN_EXTRA = "login_extra";
    private final String PASSWORD_EXTRA = "password_extra";
    private EditText mCWALoginName;
    private EditText mCWALoginPassword;
    private Button mCWALoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwalogin);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        if(PreferencesManagerUtil.isLogged(getApplicationContext())){
            goToNextActivity();
        }

        mCWALoginButton = findViewById(R.id.cwa_login_button);
        mCWALoginName = findViewById(R.id.cwa_login_name_edit_text);
//        mCWALoginPassword = findViewById(R.id.cwa_login_password_edit_text);

        if(savedInstanceState != null && savedInstanceState.containsKey(LOGIN_EXTRA)){
            mCWALoginName.setText(savedInstanceState.getString(LOGIN_EXTRA));
            mCWALoginPassword.setText(savedInstanceState.getString(PASSWORD_EXTRA));
        }

        mCWALoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

    }

    private void doLogin(){
        //will handle the custom login for each company user in the future
        PreferencesManagerUtil.setLogged(getApplicationContext(), true);
        goToNextActivity();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOGIN_EXTRA, mCWALoginName.getText().toString() );
        outState.putString(PASSWORD_EXTRA, mCWALoginPassword.getText().toString() );
    }

    private void goToNextActivity(){
        Intent intent = new Intent(this, CWABoardActivity.class);
        startActivity(intent);
        this.finish();
    }

}
