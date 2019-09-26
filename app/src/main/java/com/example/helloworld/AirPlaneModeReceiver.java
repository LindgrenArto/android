package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirPlaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (isAirplaneModeOn(context)) {
            Toast.makeText(context, "Airplane mode is ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Airplane mode is OFF", Toast.LENGTH_SHORT).show();
        }
    }
       public static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON,0) != 0;
        }
    }