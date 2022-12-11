package com.alaminkarno.bkashanimation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView moneyLeftIV,moneyRightIV;
    private TextView moneyTV;
    private LinearLayout moneyLayout;
    private  Animation animFadLeftToRight,animRightToLeft,fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();

        if(moneyTV.getText() == getString(R.string.tap_for_balance)){
            moneyLeftIV.setVisibility(View.VISIBLE);
            moneyRightIV.setVisibility(View.INVISIBLE);
        }else{
            moneyLeftIV.setVisibility(View.INVISIBLE);
            moneyRightIV.setVisibility(View.VISIBLE);
        }

        moneyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moneyTV.getText() == getString(R.string.tap_for_balance)){

                    moneyLeftIV.startAnimation(animFadLeftToRight);
                    moneyTV.startAnimation(fadeOut);

                    new CountDownTimer(2000,1000){

                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {
                            moneyTV.setText(getString(R.string.balance_amount));
                            moneyRightIV.setVisibility(View.VISIBLE);
                            moneyLeftIV.setVisibility(View.INVISIBLE);

                            new CountDownTimer(1000, 500) {
                                public void onFinish() {

                                    moneyRightIV.startAnimation(animRightToLeft);
                                    moneyTV.startAnimation(fadeOut);

                                    new CountDownTimer(2000,1000){

                                        @Override
                                        public void onTick(long l) {

                                        }

                                        @Override
                                        public void onFinish() {
                                            moneyLeftIV.setVisibility(View.VISIBLE);
                                            moneyRightIV.setVisibility(View.INVISIBLE);
                                            moneyTV.setText(getString(R.string.tap_for_balance));

                                        }
                                    }.start();
                                }

                                public void onTick(long millisUntilFinished) {
                                }
                            }.start();







                        }
                    }.start();
                }
            }
        });
    }

    void initialize(){
        moneyLeftIV = findViewById(R.id.moneyLeftIV);
        moneyRightIV = findViewById(R.id.moneyRightIV);
        moneyTV = findViewById(R.id.moneyTV);
        moneyLayout = findViewById(R.id.moneyLayout);
        animFadLeftToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.left_to_right);
        animRightToLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_to_left);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
    }
}