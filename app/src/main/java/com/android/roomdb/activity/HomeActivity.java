package com.android.roomdb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.roomdb.R;
import com.android.roomdb.recyclerview.RecycleModal;
import com.android.roomdb.recyclerview.RecyclerViewAdapter;
import com.android.roomdb.database.UserDatabase;
import com.android.roomdb.database.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity{
    public RecyclerViewAdapter recycleAdapter;
    ArrayList<UserEntity> swap;
    public RecyclerView.LayoutManager RecycleLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView nameView = (TextView) findViewById(R.id.textViewName);
        String name_view = getIntent().getExtras().getString("name_view");
        int userId = getIntent().getExtras().getInt("user_id");
        String data = "Welcome  " + name_view;
        nameView.setText(data);
        Button viewUser = findViewById(R.id.view_your_button);
        Button viewAll = findViewById(R.id.view_all_button);
        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<UserEntity> userData = UserDatabase.getDbInstance().userDao().loadAllByIds(userId);
                ArrayList<RecycleModal> tmp = new ArrayList<RecycleModal>();
                tmp.add(new RecycleModal(userData.get(0).uid,userData.get(0).name,userData.get(0).emailAddress, false));
                recycleView(tmp);
            }
        });


        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recycleView = findViewById(R.id.recycleId);
                List<UserEntity> userData = UserDatabase.getDbInstance().userDao().getAll();
                ArrayList<RecycleModal> temp = new ArrayList<>();
                for(int i = 0;i<userData.size();i++)
                {
                    temp.add(new RecycleModal(userData.get(i).uid,userData.get(i).name,userData.get(i).emailAddress, false));
                }
                recycleView(temp);


            }
        });

        Button add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(next);
            }
        });


        Button delete = findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recycleView = findViewById(R.id.recycleId);
                ArrayList<RecycleModal> sample = recycleAdapter.getSelectedData();
                if(sample!=null)
                {
                    for(int i = 0; i<sample.size();i++)
                    {
                        int uid  = sample.get(i).getUid();
                        UserEntity s =  UserDatabase.getDbInstance().userDao().loadById(uid);
                        UserDatabase.getDbInstance().userDao().delete(s);
                        Toast.makeText(HomeActivity.this, "Deleting User", Toast.LENGTH_SHORT).show();

                    }

                }
                List<UserEntity> userData = UserDatabase.getDbInstance().userDao().getAll();
                ArrayList<RecycleModal> temp = new ArrayList<>();
                for(int i = 0;i<userData.size();i++)
                {
                    temp.add(new RecycleModal(userData.get(i).uid,userData.get(i).name,userData.get(i).emailAddress, false));
                }
                recycleView(temp);
            }
        });


    }


    void recycleView(ArrayList<RecycleModal> tmp){
        RecyclerView recycleView = findViewById(R.id.recycleId);
        RecycleLayoutManager =new LinearLayoutManager(HomeActivity.this);
        recycleAdapter = new RecyclerViewAdapter(HomeActivity.this,tmp);
        recycleView.setLayoutManager(RecycleLayoutManager);
        recycleView.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();

    }
}
