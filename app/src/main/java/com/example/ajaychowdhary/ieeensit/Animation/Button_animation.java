package com.example.ajaychowdhary.ieeensit.Animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.ajaychowdhary.ieeensit.R;

/**
 * Created by AJAY CHOWDHARY on 03-02-2017.
 */

public class Button_animation {
    public void animateButton(View v, Context context){
        Animation pressAnim = AnimationUtils.loadAnimation(context, R.anim.button_press_anim);
        v.startAnimation(pressAnim);
    }
}
