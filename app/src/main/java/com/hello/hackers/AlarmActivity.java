package com.hello.hackers;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hello.hackers.R;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm);
        TextView tv=(TextView)findViewById(R.id.AlarmActtextView) ;
        TextView name = (TextView) findViewById(R.id.medname);
        TextView inst = (TextView) findViewById(R.id.instructions);

        Intent i=getIntent();
        int a=i.getExtras().getInt("AlarmActivityid");
        DatabaseHandler dh= new DatabaseHandler(this);
        ArrayList ar=dh.getReminder(a);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        tv.setText(date);
        name.setText(ar.get(1).toString());
        String instructions = ar.get(2).toString();
        if(instructions.compareTo("")==0)
            inst.setVisibility(View.GONE);
        else
            inst.setText(instructions);

        Button stop=(Button)findViewById(R.id.stop);

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlarmReceiver.ringtone.stop();
                if(MyApplication.isActivityVisible())
                {
                    Log.d("Cancelled Visible", "onClick: visible");
                    stopService(new Intent(AlarmActivity.this,AlarmService.class));
                    finish();
                }
                else
                {
                    Log.d("Cancelled Visible", "onClick: Not visible");
                    stopService(new Intent(AlarmActivity.this,AlarmService.class));
                    startActivity(new Intent(AlarmActivity.this,MainActivity.class));
                    finish();

                }





                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

    @Override
    public void onBackPressed() {
        AlarmReceiver.ringtone.stop();
        finish();
    }
}

