package com.example.david.edt;

/**
 * Created by david on 11/10/17.
 */

public enum Classes {

    Concurrence("Concurrence", "#FF0000"),
    ComputerVision("Computer Vision", "#00FF00"),
    Anglais("Anglais", "#0000FF"),
    Android("Android", "#00F504"),
    Adapt("Adaptation des IHM", "#F50404");

    private String name;
    private String color;

    public String getName(){
        return this.name;
    }

    public String getColor(){
        return this.color;
    }

    Classes(String name, String color){
        this.name = name;
        this.color = color;
    }
}
