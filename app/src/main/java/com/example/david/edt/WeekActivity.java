package com.example.david.edt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.RectF;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.david.edt.views.EDTWeekView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by david on 09/10/17.
 */

public class WeekActivity extends FragmentActivity {
    private Events events;
    private EDTWeekView weekView;

    private final int REQ_CODE_SPEECH_INPUT = 100;
    private final int CHECK_CODE = 0x1;
    private final int SHORT_DURATION = 1000;
    private Speaker speaker;

    TextView textbox;

    @Override
    protected void onStart(){
        super.onStart();

        checkTTS();

    }

    @Override
    public void onCreate(Bundle savedInstanteState){
        super.onCreate(savedInstanteState);
        setContentView(R.layout.activity_week);

        textbox = (TextView) findViewById(R.id.textbox);
        textbox.setText("Reco vocale");

        events = new Events();
        events.generateEvents();
        Log.v("CLASSES",Classes.values()[0].toString());


        weekView = (EDTWeekView)  findViewById(R.id.weekView);
        initMonth();


        Calendar today = Calendar.getInstance();
        Log.v("TODAYCAL", today.toString());


    }

    private void initMonth(){
        weekView.goToHour(8.00);
        weekView.setHourHeight(144);
        weekView.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                ArrayList<WeekViewEvent> eventsMonth = new ArrayList<WeekViewEvent>();
                List<WeekViewEvent> allEvents = events.getEvents();

                for(int i = 0; i < allEvents.size(); i++)
                    if(allEvents.get(i).getStartTime().get(Calendar.MONTH) == newMonth)
                        eventsMonth.add(allEvents.get(i));

                return eventsMonth;
            }
        });

        weekView.setOnEventClickListener(new WeekView.EventClickListener() {
            @Override
            public void onEventClick(WeekViewEvent event, RectF eventRect) {
                goToNextCourse();
            }
        });

        weekView.setEmptyViewLongPressListener(new WeekView.EmptyViewLongPressListener() {
            @Override
            public void onEmptyViewLongPress(Calendar time) {
                Log.v("LONGCLICK", "Long click");
                //speakOut("Bonjour");
                promptSpeechInput();
            }
        });
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

    private void speakOut(String text){
        Log.v("SPEAKING", "test");
        if(!speaker.isSpeaking())
            speaker.speach(text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case CHECK_CODE:
                if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    speaker = new Speaker(this);

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


                    textbox.setText(result.get(0));
                    speakOut("Concurrence demain matin à 8h30 en O308?");
                }
                break;

            default:
                break;
        }
    }

    public String goToNextCourse(){
        Log.v("NEXTCOURSE","Next course");

        Calendar today = Calendar.getInstance();

        List<WeekViewEvent> weekViewEvents = events.getEvents();
        for(int i = 0; i < weekViewEvents.size(); i++) {
            WeekViewEvent event = weekViewEvents.get(i);
            //Log.v("COURSE", weekViewEvents.get(i).getName());
            if(today.compareTo(event.getStartTime()) < 0){
                Log.v("COURSE", event.getName() + " , " + event.getStartTime().get(Calendar.HOUR_OF_DAY));
                Calendar date = (Calendar) event.getStartTime().clone();
                weekView.goToDate(date);
                weekView.goToHour(event.getStartTime().get(Calendar.HOUR_OF_DAY));
                return event.getName();
            }
        }

        Log.v("COURSE", "Nothing found");
        return null;
    }

    @Override
    protected void onStop(){
        // speaker.shutdown();
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
