package com.example.bodymeasurements_vton;

public class RecyclerViewData {
    public static String[] parts = new String[]{
      "Shoulder",
      "Right Hand",
      "Left Hand",
      "Right Leg",
      "Left Leg",
      "Hip",
      "Waist",
      "Chest Girth",
      "Thigh Girth",
      "Ankle Regular",
      "Ankle Fit"
    };

 public static  Float[] measurements = new Float[11];

    public RecyclerViewData(Float[] measurements){
        this.measurements = measurements.clone();
    }

}
