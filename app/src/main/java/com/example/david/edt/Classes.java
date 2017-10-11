package com.example.david.edt;

/**
 * Created by david on 11/10/17.
 */

public enum Classes {

    Concurrence("Concurrence", "#FF0000", "E+131", "E+134"),
    ComputerVision("Computer Vision", "#00FF00", "Amphi Ouest", "0107"),
    Anglais("Anglais", "#0000FF", "O308", "O308"),
    Android("Android", "#00F504", "Amphi Forum", "O310"),
    Chinois("Chinois", "#F9F900", "O108", "O108"),
    CPP("C++", "#6088FF", "E+130", "O107"),
    COO("COO", "#8761FF", "E+130", "O109"),
    Compilation("Compilation", "#309999", "Amphi Est", "O107"),
    Management("Management","#BC0083", "E-207", "E-207"),
    Web("Web sémantique", "#F97C00", "O310", "O310"),
    Cryptographie("Cryptographie", "#36B400", "O106", "O106"),
    Adapt("Adaptation des IHM", "#F50404", "O309", "O309");

    private String name;
    private String color;
    private String amphi;
    private String td;

    public String getName(){
        return this.name;
    }

    public String getColor(){
        return this.color;
    }

    public String getAmphi(){
        return this.amphi;
    }

    public String getTd(){
        return this.td;
    }

    Classes(String name, String color, String amphi, String td){
        this.name = name;
        this.color = color;
        this.amphi = amphi;
        this.td = td;
    }
}
