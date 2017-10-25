package com.example.david.edt;

/**
 * Created by david on 12/10/17.
 */

public class VoicePattern {

    private final String NEXT_COURSE = "prochain cours";

    public VoicePattern(){

    }

    public boolean isNextCours(String str){
        return str.contains(NEXT_COURSE);
    }
}





