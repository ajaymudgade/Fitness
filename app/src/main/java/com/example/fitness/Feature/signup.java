package com.example.fitness.Feature;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.fitness.Database.DbManager;
import com.example.fitness.R;

public class signup extends AppCompatActivity {
    EditText edname, edemail, edphone, edpassword;
    String name, email, phone, password;
    DbManager dbManager = new DbManager();
    boolean flag = false;

    AppCompatButton btn_Singup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        edname = findViewById(R.id.edname);
        edemail = findViewById(R.id.edemail);
        edphone = findViewById(R.id.edphone);
        edpassword = findViewById(R.id.edpassword);
        btn_Singup = findViewById(R.id.btn_Singup);

        btn_Singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edname.getText().toString();
                email = edemail.getText().toString();
                phone = edphone.getText().toString();
                password = edpassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()){
                   dbManager.insertUserRecord(name, email, phone, password);
                   flag = true;
                   if (flag == true){
                       Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                   }
                }
            }
        });
    }
}
