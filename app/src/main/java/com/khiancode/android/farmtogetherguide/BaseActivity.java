package com.khiancode.android.farmtogetherguide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {
    MediaPlayer mpEffect;
    protected void setFullScreen() {
        //make translucent statusBar on kitkat devices
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    protected static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void createSoundActionDenied(Context context) {
        int r = (int )(Math.random() * 2 + 1);
        int sound = R.raw.panel_open_1;

        if (r == 1) {
            sound = R.raw.action_denied_1;
        } else{
            sound = R.raw.action_denied_2;
        }
        MediaPlayer.create(context, sound).start();
    }

    public void createSoundOpenPage(Context context) {
        int r = (int )(Math.random() * 3 + 1);
        int sound = R.raw.panel_open_1;

        if (r == 1) {
            sound = R.raw.panel_open_1;
        } else if (r == 2) {
            sound = R.raw.panel_open_2;
        } else {
            sound = R.raw.panel_open_3;
        }

        playSound(context, sound);
    }

    public void createSoundClosePage(Context context) {
        int r = (int )(Math.random() * 3 + 1);
        int sound = R.raw.panel_open_1;

        if (r == 1) {
            sound = R.raw.panel_open_1;
        } else if (r == 2) {
            sound = R.raw.panel_open_2;
        } else {
            sound = R.raw.panel_open_3;
        }

        playSound(context, sound);
    }

    public void playSound(Context context, int resId) {
        stopIfPlating();
        mpEffect = MediaPlayer.create(context, resId);
        mpEffect.start();
        mpEffect.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    protected void stopIfPlating() {
        try {
            if (mpEffect != null && mpEffect.isPlaying()) {
                mpEffect.stop();
                mpEffect.release();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
