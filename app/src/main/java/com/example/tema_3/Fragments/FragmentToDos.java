package com.example.tema_3.Fragments;

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
import com.example.tema_3.Adapter.ExampleAdapterToDos;
import com.example.tema_3.ExampleTaskToDo.ExampleToDos;
import com.example.tema_3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.tema_3.Activities.ToDosActivity.EXTRA_TITLE_TASK;


public class FragmentToDos extends Fragment  implements ExampleAdapterToDos.OnItemClickListener {

    private RecyclerView mRecyclerViewToDos;
    private ExampleAdapterToDos mExampleAdapterToDos;
    private ArrayList<ExampleToDos> mExampleToDos;
    private RequestQueue mRecQueue;
    int id;

    public FragmentToDos() { }

    public void setId(int idUser) {
            id = idUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_todos, container, false);

        mRecyclerViewToDos = (RecyclerView) view.findViewById(R.id.recycler_view_todos);
        mRecyclerViewToDos.setHasFixedSize(true);
        mRecyclerViewToDos.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExampleToDos = new ArrayList<>();

        mRecQueue = Volley.newRequestQueue(getActivity());

        EXTRA_TITLE_TASK = null; // pentru titlul alarmei, trebuie initializat cu null pentru a nu pune un task ramas de la alt user

        parseJSONtodos();

        return view;
    }

    private void parseJSONtodos(){
        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;

                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject user = jsonArray.getJSONObject(i);

                                String title = user.getString("title");
                                String completed = user.getString("completed");

                                mExampleToDos.add(new ExampleToDos(title, completed));
                            }

                            mExampleAdapterToDos = new ExampleAdapterToDos(getActivity(), mExampleToDos);
                            mRecyclerViewToDos.setAdapter(mExampleAdapterToDos);
                            mExampleAdapterToDos.setOnItemClickListenerToDos(new ExampleAdapterToDos.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    ExampleToDos clickedtask = mExampleToDos.get(position);
                                    EXTRA_TITLE_TASK = clickedtask.getTitle(); }
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

        mRecQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {

        ExampleToDos clickedtask = mExampleToDos.get(position);
        EXTRA_TITLE_TASK = clickedtask.getTitle();
    }
}
