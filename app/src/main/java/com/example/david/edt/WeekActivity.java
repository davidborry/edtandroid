package com.example.david.edt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TableLayout;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.david.edt.fragments.EDTWeekFragment;
import com.example.david.edt.views.EDTWeekView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by david on 09/10/17.
 */

public class WeekActivity extends FragmentActivity {
    private static final int NUM_PAGES = 5;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    private Events events;

    @Override
    public void onCreate(Bundle savedInstanteState){
        super.onCreate(savedInstanteState);
        setContentView(R.layout.activity_week);

        events = new Events();
        events.generateEvents();
        Log.v("CLASSES",Classes.values()[0].toString());

        /*mPager = (ViewPager) findViewById(R.id.edtweek);
        mPagerAdapter = new EDTWeekFragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);*/

        EDTWeekView tabLayout = (EDTWeekView)  findViewById(R.id.weekView);
        tabLayout.goToHour(8.00);
        tabLayout.setHourHeight(144);


        tabLayout.setMonthChangeListener(new MonthLoader.MonthChangeListener() {
            @Override
            public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
                ArrayList<WeekViewEvent> eventsMonth = new ArrayList<WeekViewEvent>();
                List<WeekViewEvent> allEvents = events.getEvents();

                for(int i = 0; i < allEvents.size(); i++)
                    if(allEvents.get(i).getStartTime().get(Calendar.MONTH) == newMonth)
                        eventsMonth.add(allEvents.get(i));

               /* List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();


              Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 8);
                startTime.set(Calendar.MINUTE, 0);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, 10);
                endTime.set(Calendar.MONTH, newMonth - 1);
                WeekViewEvent event = new WeekViewEvent(1, "Concurrence", startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_01));

                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 10);
                startTime.set(Calendar.MINUTE, 15);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, 12);
                endTime.set(Calendar.MINUTE, 15);
                endTime.set(Calendar.MONTH, newMonth-1);
                event = new WeekViewEvent(10, "Anglais", startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_02));
                events.add(event);

                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, 13);
                startTime.set(Calendar.MINUTE, 30);
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, 15);
                endTime.set(Calendar.MINUTE, 30);
                event = new WeekViewEvent(10, "Computer Vision", startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_03));
                events.add(event);*/



                return eventsMonth;
            }
        });

        //tabLayout.setupWithViewPager(mPager);

    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    private class EDTWeekFragmentAdapter extends FragmentStatePagerAdapter{
        public EDTWeekFragmentAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            return new EDTWeekFragment();
        }

        @Override
        public int getCount(){
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return "TITRE";
        }
    }
}
