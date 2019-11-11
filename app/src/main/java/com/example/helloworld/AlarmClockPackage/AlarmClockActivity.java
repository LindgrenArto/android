package com.example.helloworld.AlarmClockPackage;


import android.app.Activity ;
import android.os.Bundle ;
import android.graphics.* ;  // Classes Canvas, Color, Paint, RectF, etc.
import android.view.* ;      // Classes View, Display, WindowManager, etc.
import android.widget.* ;
import android.content.Context ;

import java.util.Calendar ;
import java.util.GregorianCalendar ;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;

public class AlarmClockActivity extends AppCompatActivity
    {
        AlarmClockView alarm_clock_view;

        public void onCreate( Bundle savedInstanceState )
        {
            super.onCreate( savedInstanceState ) ;

            setContentView( R.layout.activity_alarm_clock ) ;

            alarm_clock_view = (AlarmClockView) findViewById( R.id.alarm_clock_view ) ;
        }

        public void onStart()
        {
            super.onStart() ;

            alarm_clock_view.start_animation_thread() ;
        }

        public void onStop()
        {
            super.onStop() ;

            alarm_clock_view.stop_animation_thread() ;
        }

        public void set_alarm_button_clicked( View view )
        {
            Button set_alarm_button = (Button) view ;

            if ( set_alarm_button.getText().equals( "End Set Alarm" ) )
            {
                // We are already setting the alarm. Let's stop it.
                set_alarm_button.setText( "Set Alarm" ) ;
                set_alarm_button.setTextColor( Color.BLACK ) ;

                alarm_clock_view.disable_alarm_time_setting() ;
            }
            else
            {
                set_alarm_button.setText( "End Set Alarm" ) ;
                set_alarm_button.setTextColor( Color.RED ) ;

                alarm_clock_view.enable_alarm_time_setting() ;
            }
        }

        public void activate_alarm_button_clicked( View view )
        {
        }
    }
