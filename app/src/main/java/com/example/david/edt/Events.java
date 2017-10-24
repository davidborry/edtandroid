package com.example.david.edt;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

        mEvents.add(makeTD(Classes.Concurrence, 23, 10, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.ComputerVision, 23, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Anglais, 23, 10, 2017, 13, 30, 15, 30));

        mEvents.add(makeClass(Classes.CPP, 24, 10, 2017, 8, 0, 9, 0));
        mEvents.add(makeClass(Classes.Cryptographie, 24, 10, 2017, 9, 0, 10, 0));
        mEvents.add(makeTD(Classes.COO, 24, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.CPP, 24, 10, 2017, 13, 30, 15, 30));
        mEvents.add(makeTD(Classes.Management, 24, 10, 2017, 15, 45, 17, 45));

        mEvents.add(makeClass(Classes.Android, 25, 10, 2017, 8, 0, 9, 0));
        mEvents.add(makeClass(Classes.Web, 25, 10, 2017, 9, 0, 10, 0));
        mEvents.add(makeTD(Classes.Android, 25, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Cryptographie, 25, 10, 2017, 13, 30, 15, 30));
        mEvents.add(makeTD(Classes.Chinois, 25, 10, 2017, 15, 45, 17, 45));

        mEvents.add(makeTD(Classes.Android, 26, 10, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.Web, 26, 10, 2017, 10, 15, 12, 15));

        mEvents.add(makeTD(Classes.Compilation, 27, 10, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.COO, 27, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Management, 27, 10, 2017, 13, 30, 18, 15));

        mEvents.add(makeTD(Classes.Concurrence, 30, 10, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.ComputerVision, 30, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Anglais, 30, 10, 2017, 13, 30, 15, 30));

        mEvents.add(makeClass(Classes.CPP, 31, 10, 2017, 8, 0, 9, 0));
        mEvents.add(makeClass(Classes.Cryptographie, 31, 10, 2017, 9, 0, 10, 0));
        mEvents.add(makeTD(Classes.COO, 31, 10, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.CPP, 31, 10, 2017, 13, 30, 15, 30));
        mEvents.add(makeTD(Classes.Management, 31, 10, 2017, 15, 45, 17, 45));

        mEvents.add(makeClass(Classes.Android, 1, 11, 2017, 8, 0, 9, 0));
        mEvents.add(makeClass(Classes.Web, 1, 11, 2017, 9, 0, 10, 0));
        mEvents.add(makeTD(Classes.Android, 1, 11, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Cryptographie, 1, 11, 2017, 13, 30, 15, 30));
        mEvents.add(makeTD(Classes.Chinois, 1, 11, 2017, 15, 45, 17, 45));

        mEvents.add(makeTD(Classes.Android, 2, 11, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.Web, 2, 11, 2017, 10, 15, 12, 15));

        mEvents.add(makeTD(Classes.Compilation, 3, 11, 2017, 8, 0, 10, 0));
        mEvents.add(makeTD(Classes.COO, 3, 11, 2017, 10, 15, 12, 15));
        mEvents.add(makeTD(Classes.Management, 3, 11, 2017, 13, 30, 18, 15));


        Collections.sort(mEvents, new Comparator<WeekViewEvent>() {
            @Override
            public int compare(WeekViewEvent o1, WeekViewEvent o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });


    }


    public WeekViewEvent makeClass(Classes className, int day, int month, int year, int hourStart, int minStart, int hourEnd, int minEnd){
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
        event.setLocation(className.getAmphi());
        return event;
    }

    public WeekViewEvent makeTD(Classes className, int day, int month, int year, int hourStart, int minStart, int hourEnd, int minEnd){
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
        event.setLocation(className.getTd());
        return event;
    }



    public List<WeekViewEvent> getEvents(){
        return this.mEvents;
    }

    private List<WeekViewEvent> mEvents;
    private int id;
}
