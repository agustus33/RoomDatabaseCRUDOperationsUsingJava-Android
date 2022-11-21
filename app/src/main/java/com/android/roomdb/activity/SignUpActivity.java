package com.android.roomdb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.roomdb.R;
import com.android.roomdb.database.UserDatabase;
import com.android.roomdb.database.UserEntity;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {
    Button btn;
    TextInputLayout name, email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.nameLayout);
        email = findViewById(R.id.emailLayout);
        pwd = findViewById(R.id.pwdLayout);
        btn = findViewById(R.id.button_s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewUser(name.getEditText().getText().toString(), email.getEditText().getText().toString(), pwd.getEditText().getText().toString());
            }
        });
    }

    private void saveNewUser(String name, String emailAddress, String password) {
        Log.e("testing", "db passed");
        UserEntity user = new UserEntity(name, emailAddress, password);
        UserDatabase.getDbInstance().userDao().insertUser(user);

        Log.e("testing", "inserted success");

        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();

    }

}