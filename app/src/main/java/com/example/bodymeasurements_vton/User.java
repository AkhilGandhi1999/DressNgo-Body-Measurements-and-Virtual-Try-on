package com.example.bodymeasurements_vton;

import org.json.JSONException;

import java.util.HashMap;

public class User {
    public String email;
    public int flag;
    public String name, gender;
    public int height, weight;
    public HashMap<String,String> measurements = new HashMap<String, String>();
   // public JSONObject measurements = new JSONObject();
   // public float shoulder,right_hand, left_hand, right_leg, left_leg, hip, waist, chest_girth, thigh_girth, ankle_regular, ankle_tight;


    public User(){

    }
    public User(String email, int flag) throws JSONException {
        this.email = email;
        this.flag = flag;
        this.name = this.gender = "";
        this.height = this.weight = 0;
        measurements.put("shoulder", "0");
        measurements.put("right_hand","0");
        measurements.put("left_hand", "0");
        measurements.put("right_leg", "0");
        measurements.put("left_leg", "0");
        measurements.put("hip", "0");
        measurements.put("waist", "0");
        measurements.put("chest_girth", "0");
        measurements.put("thigh_girth", "0");
        measurements.put("ankle_regular", "0");
        measurements.put("ankle_tight", "0");

//        this.measurements.put("shoulder", "0");
//        this.measurements.put("right_hand", "0");
//        this.measurements.put("left_hand", "0");
//        this.measurements.put("right_leg", "0");
//        this.measurements.put("left_leg", "0");
//        this.measurements.put("hip", "0");
//        this.measurements.put("waist", "0");
//        this.measurements.put("chest_girth", "0");
//        this.measurements.put("thigh_girth", "0");
//        this.measurements.put("ankle_regular", "0");
//        this.measurements.put("ankle_tight", "0");
      //  this.shoulder = this.right_hand = this. left_hand = this.right_leg = this.left_leg = this.hip = this.waist = this.chest_girth = this.thigh_girth = this.ankle_regular = this.ankle_tight = (float) 0;
    }
}
