package com.khiancode.android.farmtogetherguide;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.khiancode.android.farmtogetherguide.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private MediaPlayer mpBgm;

    int stat = 0;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        if (stat == 0) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "กดอีกครั้งเพื่อออกจากแอป", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            stat = 0;
            setFram(new HomeFragment());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSoundMusic(this,R.raw.music);
        setFram(new HomeFragment());
    }

    public void setFram(Fragment fram) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, fram);
        ft.commitAllowingStateLoss();
    }

    public void playSoundMusic(Context context, int resId) {
        stopIfPlating();
        mpBgm = MediaPlayer.create(context, resId);
        mpBgm.start();
        mpBgm.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                playSoundMusic(MainActivity.this,R.raw.music);
            }
        });
    }

    public void stopIfPlatingMusic() {
        try {
            if(mpBgm != null && mpBgm.isPlaying()) {
                mpBgm.stop();
                mpBgm.release();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopIfPlatingMusic();
        stopIfPlating();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mpBgm != null && mpBgm.isPlaying()) {
            mpBgm.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpBgm != null) {
            mpBgm.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mpBgm.stop();
        mpBgm.release();
        mpBgm = null;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
