package com.xapple.gankio.util.common;

import android.app.Activity;
import android.media.AudioManager;
import android.view.Window;
import android.view.WindowManager;

/**
 * 项目名称：jianyue
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：15/11/16 下午10:34
 * 修改人：wengyiming
 * 修改时间：15/11/16 下午10:34
 * 修改备注：
 */
public class SysUtils {

    /**
     * 滑动改变亮度
     */
    public static float onBrightnessSlide(Activity activity, float change) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lpa = window.getAttributes();

        float brightness = lpa.screenBrightness;

        if (brightness <= 0.00f) {
            brightness = 0.50f;
        } else if (brightness < 0.01f) {
            brightness = 0.01f;
        }

        lpa.screenBrightness = brightness + change;

        if (lpa.screenBrightness > 1.0f) {
            lpa.screenBrightness = 1.0f;
        } else if (lpa.screenBrightness < 0.01f) {
            lpa.screenBrightness = 0.01f;
        }

        window.setAttributes(lpa);

        return lpa.screenBrightness;
    }


    /**
     * 滑动改变音量
     */
    public static int onVolumeSlide(AudioManager audioManager, int stepVolume, float distanceY, float change) {
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

//        //无缓冲
//        int index = (int) (change * maxVolume) + currentVolume;
//        if (index > maxVolume) {
//            index = maxVolume;
//        } else if (index < 0) {
//            index = 0;
//        }
//
        //移动5dp以上才改变
        if (distanceY >= stepVolume) {
            if (currentVolume < maxVolume) {
                currentVolume++;
            }
        } else if (distanceY <= -stepVolume) {
            if (currentVolume > 0) {
                currentVolume--;
            }
        }

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);

        return (currentVolume * 100) / maxVolume;
    }
}
