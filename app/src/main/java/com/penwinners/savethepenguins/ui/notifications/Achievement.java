package com.penwinners.savethepenguins.ui.notifications;

public class Achievement {

    public boolean is_title;
    public int negative_weight;
    public int positive_weight;
    public String text;

    public Achievement(String str) {
        String[] out = str.split("\\|");
        is_title = out[0].equals("T");
        negative_weight = Integer.parseInt(out[1]);
        positive_weight = Integer.parseInt(out[2]);
        text = out[3];
    }

}