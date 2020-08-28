package com.hello.hackers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OnBoardActivity extends AppCompatActivity {
     ViewPager viewPager;
     TextView next,skip;
     ImageSlider slider;
    int[] imagesliderId = {R.drawable.bs1,R.drawable.bs2,R.drawable.bs3};
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        viewPager=findViewById(R.id.onboard_vP);
        slider=new ImageSlider(this,imagesliderId);
        viewPager.setAdapter(slider);
        skip=findViewById(R.id.skip);
        next=findViewById(R.id.next);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OnBoardActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if(pos==imagesliderId.length-1)
               {
                   startActivity(new Intent(OnBoardActivity.this,MainActivity.class));
               }
                pos++;
                viewPager.setCurrentItem(pos, true);


            }
        });


    }
}