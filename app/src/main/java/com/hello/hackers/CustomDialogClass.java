package com.hello.hackers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hello.hackers.R;



public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    TextView textView;
    public Dialog d;
    int img=0;
    ImageView g1,g2,g3,b1,b2,b3,g4,g5,g6,b4,b5,b6;
    Context con;

    public CustomDialogClass(Activity a, Context c) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        con=c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        g1=(ImageView)findViewById(R.id.g1);
        g2=(ImageView)findViewById(R.id.g2);
        g3=(ImageView)findViewById(R.id.g3);
        b1=(ImageView)findViewById(R.id.b1);
        b2=(ImageView)findViewById(R.id.b2);
        b3=(ImageView)findViewById(R.id.b3);
        g4=findViewById(R.id.g4);
        g5=findViewById(R.id.g5);
        g6=findViewById(R.id.g6);
        b4=findViewById(R.id.b4);
        b5=findViewById(R.id.b5);
        b6=findViewById(R.id.b6);
        textView=(TextView)findViewById(R.id.dia_name) ;
        g1.setOnClickListener(this); g2.setOnClickListener(this); g3.setOnClickListener(this);
        b1.setOnClickListener(this);b2.setOnClickListener(this);b3.setOnClickListener(this);
        g4.setOnClickListener(this); g5.setOnClickListener(this); g6.setOnClickListener(this);
        b4.setOnClickListener(this);b5.setOnClickListener(this);b6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.g1:
                img=R.drawable.g1;
                break;
            case R.id.g2:
                img=R.drawable.g2;
                break;
            case R.id.g3:
                img=R.drawable.g3;
                break;
            case R.id.b1:
                img=R.drawable.b1;
                break;
            case R.id.b2:
                img=R.drawable.b2;
                break;
            case R.id.b3:
                img=R.drawable.b3;
                break;
            case R.id.g4:
                img=R.drawable.g4;
                break;
            case R.id.g5:
                img=R.drawable.g5;
                break;
            case R.id.g6:
                img=R.drawable.g6;
                break;
            case R.id.b4:
                img=R.drawable.b4;
                break;
            case R.id.b5:
                img=R.drawable.b5;
                break;
            case R.id.b6:
                img=R.drawable.b6;
                break;
            default:
                break;
        }

        String s=textView.getText().toString();
        Intent in=new Intent(con,MainActivity.class);
        in.putExtra("id",img);
        in.putExtra("text",s);

        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        con.startActivity(in);
        c.finish();

        dismiss();
    }

}