package jtli.com.simplereader.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jtli.com.simplereader.R;
import jtli.com.simplereader.ui.activity.base.BaseActivity;

/**
 * Created by Jingtian(Tansent).
 */

public class SplashActivity extends BaseActivity{

    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @BindView(R.id.activity_transition)
    RelativeLayout activityTransit;

    private Unbinder bind;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
        ivPic.setImageResource(R.drawable.splash);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                toMainActivity();
//            }
//        }, 2000);
        startAnim();
    }


    /**
     * start animation
     */
    private void startAnim() {

        // animation set
        AnimationSet set = new AnimationSet(false);

        // add rotate animation
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);// animation period
        rotate.setFillAfter(true);// let this animation performs persist

        // add scale animation
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);// animation period
        scale.setFillAfter(true);// let this animation performs persist

        // add alpha animation
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);// animation period
        alpha.setFillAfter(true);// let this animation performs persist

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        /**
         * set animation listener (what to do when animation starts, repeat, end)
         */
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // when animation ends
            @Override
            public void onAnimationEnd(Animation animation) {
                toMainActivity();
            }
        });

        activityTransit.startAnimation(set);
    }


    private void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

}
