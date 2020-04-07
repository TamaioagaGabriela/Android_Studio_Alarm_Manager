package com.example.tema_3.ExampleUser;

public class ExampleItem {
    private String mName;
    private String mUsername;
    private String mEmail;

    public ExampleItem(String name, String username, String email){
        mName = name;
        mUsername = username;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }
}
