package com.example.bodymeasurements_vton;

public class User {
    public String email;
    public int flag;
    public String name, gender;
    public int height, weight;

    public User(){

    }
    public User(String email, int flag){
        this.email = email;
        this.flag = flag;
        this.name = this.gender = "";
        this.height = this.weight = 0;
    }
}
