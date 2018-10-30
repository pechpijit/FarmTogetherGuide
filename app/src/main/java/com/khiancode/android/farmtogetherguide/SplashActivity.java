package com.khiancode.android.farmtogetherguide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.imgLogo)
    ImageView imgLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        playSound(this, R.raw.logo_appear);

        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(imgLogo);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        finish();
                    }
                },3000);

    }

    @Override
    public void onStop() {
        super.onStop();
        stopIfPlating();
    }
}
