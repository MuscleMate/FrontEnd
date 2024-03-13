package com.example.musclematefront.parsers;

import com.example.musclematefront.models.User;
import com.google.gson.Gson;

public class UserParser {

    public User parseUser(String json) {
        return new Gson().fromJson(json, User.class);
    }
}
