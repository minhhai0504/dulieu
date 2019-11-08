package com.example.dulieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText edtUser;
    private EditText edtPass;


//aaaaaa
    ///kfhkafkaskvd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fdfffddffsd
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
    }

    public void btnLogin(View view) {
        if (edtUser.getText().toString().equals("") && edtPass.getText().toString().equals("")){
            Toast.makeText(this, "mời bạn nhập tài khoản và mật khẩu ", Toast.LENGTH_SHORT).show();
        }else if (edtUser.getText().toString().equals("Hai") && edtPass.getText().toString().equals("hai")){
            Intent intent =new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }


    }
}
