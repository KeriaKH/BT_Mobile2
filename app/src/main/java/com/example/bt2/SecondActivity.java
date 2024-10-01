package com.example.bt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    ArrayList<User> users;
    UserListAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Intent intent=getIntent();
        users=(ArrayList<User>)intent.getSerializableExtra("userList");
        listView=findViewById(R.id.listview);
        adapter=new UserListAdapter(this,R.layout.item,users);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemid=item.getItemId();
        if(itemid==R.id.option1){
            Intent addintent=new Intent(this,AddUser.class);
            startActivityForResult(addintent,1);
            return true;
        } else if (itemid == R.id.option2) {
            List<User> studentsToRemove = new ArrayList<>();
            for (User user : users) {
                if (user.isSelected==true) {
                    studentsToRemove.add(user);
                }
            }
            users.removeAll(studentsToRemove);
            UserListAdapter adapter = new UserListAdapter(this, R.layout.item, users);
            listView.setAdapter(adapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String password = data.getStringExtra("password");
            users.add(new User(name, password));
            adapter.notifyDataSetChanged();
        }
    }
}

