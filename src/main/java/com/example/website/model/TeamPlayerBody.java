package com.example.website.model;

import lombok.Data;

@Data
public class TeamPlayerBody extends Player{
    private String userName;
    private String newName;

    public TeamPlayerBody(){

    }
    public TeamPlayerBody(String userName, String newName){
        this.userName = userName;
        this.newName = newName;
    }
}
