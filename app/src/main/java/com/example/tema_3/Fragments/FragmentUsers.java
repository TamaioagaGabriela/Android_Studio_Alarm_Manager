package com.example.tema_3.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tema_3.Adapter.ExampleAdapter;
import com.example.tema_3.ExampleUser.ExampleItem;
import com.example.tema_3.R;
import com.example.tema_3.Activities.ToDosActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.tema_3.Activities.MainActivity.EXTRA_ID;
import static com.example.tema_3.Activities.MainActivity.EXTRA_NAME;

public class FragmentUsers extends Fragment implements ExampleAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    public FragmentUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_users, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();

        return v;
    }


    private void parseJSON(){
        String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR15vPnHNidgKWEc7WPlsPAIjbyFfXuoXyemk0sfBmZRphxhWSrl7mowXzc";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject user = jsonArray.getJSONObject(i);

                                String name = user.getString("name");
                                String username = user.getString("username");
                                String email = user.getString("email");

                                mExampleList.add(new ExampleItem(name, username, email));
                            }

                            mExampleAdapter = new ExampleAdapter(getActivity(), mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent todosIntent = new Intent(getActivity(), ToDosActivity.class);

                                    ExampleItem clickedItem = mExampleList.get(position);

                                    todosIntent.putExtra(EXTRA_NAME, clickedItem.getName());
                                    todosIntent.putExtra(EXTRA_ID, position);

                                    startActivity(todosIntent); }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Intent todosIntent = new Intent(getActivity(), ToDosActivity.class);

        ExampleItem clickedItem = mExampleList.get(position);

        todosIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        todosIntent.putExtra(EXTRA_ID, position);

        startActivity(todosIntent);
    }
}