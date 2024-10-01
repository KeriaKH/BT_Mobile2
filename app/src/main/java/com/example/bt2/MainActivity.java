package com.example.bt2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class User implements Serializable {
    public String name;
    public String password;
    public boolean isSelected;

    User(String name, String password) {
        this.name = name;
        this.password = password;
        this.isSelected = false;
    }
}
public class MainActivity extends AppCompatActivity {

    private EditText name, password;
    private Button button;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();
        userList.add(new User("user1", "pass1"));
        userList.add(new User("user2", "pass2"));
        userList.add(new User("user3", "pass3"));
        userList.add(new User("user4", "pass4"));
        userList.add(new User("user5", "pass5"));
        userList.add(new User("user6", "pass6"));
        userList.add(new User("user7", "pass7"));
        userList.add(new User("user8", "pass8"));
        userList.add(new User("user9", "pass9"));
        userList.add(new User("user10", "pass10"));

        name = findViewById(R.id.user);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUser(name.getText().toString(), password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("userList", (Serializable) userList);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean checkUser(String name, String password) {
        for (User user : userList) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}

class UserListAdapter extends ArrayAdapter<User> {
    int resource;
    private List<User> users;

    public UserListAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
        this.users = users;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(this.resource, null);
        }

        User user = getItem(position);
        if (user != null) {
            CheckBox checkBox = v.findViewById(R.id.checkBox);
            TextView nameTextView = v.findViewById(R.id.textView);
            TextView passwordTextView = v.findViewById(R.id.textView2);
            if (nameTextView != null) {
                nameTextView.setText(user.name);
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    user.isSelected = isChecked;
                });
            }
            if (passwordTextView != null) {
                passwordTextView.setText(user.password);
            }
        }
        return v;
    }
}