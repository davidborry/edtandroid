package com.example.david.edt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int CHECK_CODE = 0x1;
    private final int SHORT_DURATION = 1000;
    TextView text;
    Button button, button2;
    private VoiceSynth voiceSynth;


    @Override
    protected void onStart(){
        super.onStart();

        checkTTS();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text) ;
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut("Prochain cours à 13 heure 30");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakOut("Prochain cours en O 308");
            }
        });

        //promptSpeechInput();
    }


    private void speakOut(String text){
        Log.v("SPEAKING", "test");
        if(!voiceSynth.isSpeaking())
            voiceSynth.speach(text);
    }

    private void promptSpeechInput(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().getDisplayLanguage());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Vous pouvez parler...");

        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }

        catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "La reconnaissance vocale n'est pas supportée sur cet appareil", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case CHECK_CODE:
                if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    voiceSynth = new VoiceSynth(this);

                }
                else{
                    Intent install = new Intent();
                    install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(install);
                }

                break;

            case REQ_CODE_SPEECH_INPUT:
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    text.setText(result.get(0));
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onStop(){
       // voiceSynth.shutdown();
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        checkTTS();
    }

    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
    }
}
