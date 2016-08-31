package com.zyx.group14.android1603.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
public static final String TAG = "zyx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        Log.i(TAG, "onCreate: " + c.getTimeInMillis()+" "+c.get(Calendar.MONTH));
        //1472560749 1472601600
        long i = 1472449344;
        c.setTimeInMillis(i*1000);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int sec = c.get(Calendar.SECOND);
        Log.i(TAG, "onCreate: "+ year+ " "+month+" "+day+" "+weekDay+" "+hour+" "+minute+" "+sec);
        SimpleDateFormat sdf = new SimpleDateFormat("- -"+ "MM-dd EEEE" +"- -", Locale.CHINA);
        String time = sdf.format(new Date(i * 1000));
        TextView textView = (TextView) findViewById(R.id.test);
        textView.setText(time);


    }
}
