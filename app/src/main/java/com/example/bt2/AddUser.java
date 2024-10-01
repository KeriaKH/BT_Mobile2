package com.example.bt2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUser extends AppCompatActivity{
    EditText name,password;
    Button add,cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        add=findViewById(R.id.okButton);
        cancel=findViewById(R.id.cancelButton);
        cancel.setOnClickListener(v -> {
            finish();
        });
        add.setOnClickListener(v -> {
           String nameText=name.getText().toString();
           String passwordText=password.getText().toString();
            if (nameText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(AddUser.this,"please fill all field",Toast.LENGTH_LONG).show();}
            else {
                Toast.makeText(AddUser.this,"add student successfully",Toast.LENGTH_LONG).show();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", nameText);
                resultIntent.putExtra("password",passwordText);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
