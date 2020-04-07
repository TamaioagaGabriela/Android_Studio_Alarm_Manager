package com.example.tema_3.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.example.tema_3.Fragments.FragmentButtons;
import com.example.tema_3.Fragments.FragmentToDos;
import com.example.tema_3.R;

import static com.example.tema_3.Activities.MainActivity.EXTRA_ID;
import static com.example.tema_3.Activities.MainActivity.EXTRA_NAME;


public class ToDosActivity extends AppCompatActivity{

    public static String EXTRA_TITLE_TASK = "title_task";
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        id = intent.getIntExtra(EXTRA_ID, 0);
        id = id + 1;

        TextView textViewName = findViewById(R.id.text_view_name_user);
        textViewName.setText("Name: " + name + " (id = " + id + ")");

        FragmentToDos fragmentTodos = new FragmentToDos();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        fragmentTodos.setId(id);
        ft.add(R.id.container_todos, fragmentTodos);
        final FragmentButtons fragmentButtons = new FragmentButtons();
        ft.add(R.id.container_buttons, fragmentButtons);
        ft.commit();

    }

}