package com.android.roomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.roomdb.R;
import com.android.roomdb.database.UserDatabase;
import com.android.roomdb.database.UserEntity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logInOne = findViewById(R.id.buttonLogIn);
        Button signUpOne = findViewById(R.id.SignUpOne);
        signUpOne.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                                             startActivity(intent);
                                         }});

         TextInputLayout nameId, email_Address, passwordId;

        email_Address  = findViewById(R.id.editTextLoginEmail);
        passwordId = findViewById(R.id.editTextLoginPassword);


        logInOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase db = UserDatabase.getDbInstance();
                try {
                    List<UserEntity> tmp = db.userDao().loginCheck(email_Address.getEditText().getText().toString(), passwordId.getEditText().getText().toString());
                    if ((email_Address.getEditText().getText().toString()).equals(tmp.get(0).emailAddress)) {
                        if ((passwordId.getEditText().getText().toString()).equals(tmp.get(0).password)) {
                            Log.e("test", "Successfully Logged in");
                            Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
                            intent1.putExtra("name_view", tmp.get(0).name);
                            intent1.putExtra("user_id",tmp.get(0).uid);
                            startActivity(intent1);
                        }
                    }
                    tmp.clear();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Enter The Valid Email And Password", Toast.LENGTH_SHORT).show();;
                }

            }
        });


    }
}