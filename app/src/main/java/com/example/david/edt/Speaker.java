package com.example.david.edt;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by david on 06/10/17.
 */

public class Speaker implements TextToSpeech.OnInitListener {
    private static Locale language = Locale.FRANCE;
    private static TextToSpeech tts;
    private boolean isReady = false;

    public Speaker(Context context){
        tts = new TextToSpeech(context,this);
        tts.setPitch(1.0f);
        tts.setSpeechRate(0.9f);
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            tts.setLanguage(language);
            isReady = true;
        }
        else{
            isReady = false;
        }
    }

    public void speach(String text){
        if(isReady){
            tts.speak(text, TextToSpeech.QUEUE_ADD, null,null);
        }
    }

    public void setSpeechRate(float speechRate){
        tts.setSpeechRate(speechRate);
    }

    public void setPitch(float pitch){
        tts.setPitch(pitch);
    }

    public boolean isSpeaking(){
        return tts.isSpeaking();
    }

    public void pause(int duration){
        tts.playSilentUtterance(duration, TextToSpeech.QUEUE_ADD, null);
    }

    public void stop(){
        tts.stop();
    }

    public void shutdown(){
        tts.shutdown();
    }
}
