package com.example.tema_3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema_3.ExampleTaskToDo.ExampleToDos;
import com.example.tema_3.R;

import java.util.ArrayList;

public class ExampleAdapterToDos extends RecyclerView.Adapter<ExampleAdapterToDos.ExampleViewHolder> {

    private Context mContextToDos;
    private ArrayList<ExampleToDos> mExampleToDos;
    private ExampleAdapterToDos.OnItemClickListener mListenerToDos;

    public ExampleAdapterToDos(Context context, ArrayList<ExampleToDos> exampleToDos){
        mContextToDos = context;
        mExampleToDos = exampleToDos;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContextToDos).inflate(R.layout.example_todos, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleToDos currentTask = mExampleToDos.get(position);

        String title = currentTask.getTitle();
        String completed = currentTask.getCompleted();

        holder.mTextViewTitle.setText(title);
        holder.mTextViewCompleted.setText(completed);

    }

    @Override
    public int getItemCount() {
        return mExampleToDos.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListenerToDos(OnItemClickListener listener){
        mListenerToDos = listener;
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewTitle;
        public TextView mTextViewCompleted;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mTextViewCompleted = itemView.findViewById(R.id.text_view_completed);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListenerToDos != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListenerToDos.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
