package com.example.bodymeasurements_vton;

public class User {
    public String email;
    public int flag;
    public String name, gender;
    public int height, weight;
    public double shoulder,right_hand, left_hand, right_leg, left_leg, hip, waist, chest_girth, thigh_girth, ankle_regular, ankle_tight;

    public User(){

    }
    public User(String email, int flag){
        this.email = email;
        this.flag = flag;
        this.name = this.gender = "";
        this.height = this.weight = 0;
        this.shoulder = this.right_hand = this. left_hand = this.right_leg = this.left_leg = this.hip = this.waist = this.chest_girth = this.thigh_girth = this.ankle_regular = this.ankle_tight = (double)0;
    }
}
