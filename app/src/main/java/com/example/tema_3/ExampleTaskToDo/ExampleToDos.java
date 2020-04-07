package com.example.tema_3.ExampleTaskToDo;

public class ExampleToDos {
    String mTitle;
    String mCompleted;

    public ExampleToDos(String title, String completed){
        mTitle = title;
        mCompleted = completed;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCompleted() {
        return mCompleted;
    }
}
