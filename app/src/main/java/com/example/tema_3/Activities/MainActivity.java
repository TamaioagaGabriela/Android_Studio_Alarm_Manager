package com.example.tema_3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tema_3.Fragments.FragmentUsers;
import com.example.tema_3.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUsers fragmentUsers = new FragmentUsers();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_users, fragmentUsers);
        ft.commit();

    }

}
