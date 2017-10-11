package com.example.david.edt;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by david on 11/10/17.
 */


public class Events {
    public Events(){
        this.id = 0;
        mEvents = new ArrayList<WeekViewEvent>();
    }

    public void generateEvents() {
        mEvents.add(makeEvent(Classes.Concurrence, 9, 10, 2017, 8, 0, 10, 0));
        mEvents.add(makeEvent(Classes.ComputerVision, 9, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeEvent(Classes.Anglais, 9, 10, 2017, 13, 30, 15, 30));
    }

    public WeekViewEvent makeEvent(Classes className, int day, int month, int year, int hourStart, int minStart, int hourEnd, int minEnd){
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, hourStart);
        startTime.set(Calendar.MINUTE, minStart);

        startTime.set(Calendar.DAY_OF_MONTH,day);
        startTime.set(Calendar.MONTH, month-1);
        startTime.set(Calendar.YEAR, year);

        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, hourEnd);
        endTime.set(Calendar.MINUTE, minEnd);

        WeekViewEvent event = new WeekViewEvent(this.id++,className.getName(), startTime, endTime);
        event.setColor(Color.parseColor(className.getColor()));
        return event;
    }

    public List<WeekViewEvent> getEvents(){
        return this.mEvents;
    }

    private List<WeekViewEvent> mEvents;
    private int id;
}
