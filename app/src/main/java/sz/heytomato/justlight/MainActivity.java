package sz.heytomato.justlight;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;


/**
 * appName:只是灯
 * author:Heytomato
 * version：1.0
 * */

public class MainActivity extends Information {


    public void onClick_ToFlashlight(View view){
        hideAllUI();
        UIFlashlight.setVisibility(View.VISIBLE);
        lastUIType=UIType.UI_TYPE_FLASHLIGHT;
        currentUIType=UIType.UI_TYPE_FLASHLIGHT;
    }

    public void onClick_ToWarningLight(View view){
        hideAllUI();
        UIWarningLight.setVisibility(View.VISIBLE);
        lastUIType=UIType.UI_TYPE_WARNINGLIGHT;
        currentUIType=lastUIType;
        screenBrightness(1f);
        new WarningLightThread().start();
    }

    public void onClick_ToScrnLight(View view){
        hideAllUI();
        UIScrnLight.setVisibility(View.VISIBLE);
        lastUIType=UIType.UI_TYPE_SCRNLIGHT;
        currentUIType=lastUIType;
        screenBrightness(0.7f);
    }
    public void onClick_ToColorfulLight(View view){
        hideAllUI();
        UIColorLight.setVisibility(View.VISIBLE);
        lastUIType=UIType.UI_TYPE_COLORFULLIGHT;
        currentUIType=lastUIType;
        screenBrightness(0.7f);
        new ColorfulThread().start();

    }
    public void onClick_ToInformation(View view){
        hideAllUI();
        UIInformation.setVisibility(View.VISIBLE);
        lastUIType=UIType.UI_TYPE_INFORMATION;
        currentUIType=lastUIType;

    }


    public void onClick_Controller(View view){
        TransitionDrawable drawable =(TransitionDrawable)imageViewFlashlight.getDrawable();
        if ((( Boolean)imageViewFlashlight.getTag())){
            drawable.reverseTransition(25);
            imageViewFlashlight.setTag(false);
            if (camera!=null){
                parameters=camera.getParameters();
                parameters.setFlashMode(parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.stopPreview();
                camera.release();
                camera=null;

            }
        }

        hideAllUI();


        if (currentUIType!=UIType.UI_TYPE_MAIN){
            UIMain.setVisibility(View.VISIBLE);
            currentUIType=UIType.UI_TYPE_MAIN;
            warningLightFlicker=false;
            colorState=false;
            screenBrightness(defoultScreenBrightness);
        }else {
            switch (lastUIType){
                case UI_TYPE_FLASHLIGHT:
                    UIFlashlight.setVisibility(View.VISIBLE);
                    currentUIType=UIType.UI_TYPE_FLASHLIGHT;

                    break;
                case UI_TYPE_WARNINGLIGHT:
                    UIWarningLight.setVisibility(View.VISIBLE);
                    currentUIType=UIType.UI_TYPE_WARNINGLIGHT;
                    screenBrightness(1f);
                    new WarningLightThread().start();
                    break;
                case UI_TYPE_SCRNLIGHT:
                    UIScrnLight.setVisibility(View.VISIBLE);
                    currentUIType=UIType.UI_TYPE_SCRNLIGHT;
                    screenBrightness(0.7f);

                    break;
                case UI_TYPE_COLORFULLIGHT:
                    UIColorLight.setVisibility(View.VISIBLE);
                    currentUIType=UIType.UI_TYPE_COLORFULLIGHT;
                    screenBrightness(0.7f);
                    new ColorfulThread().start();
                    break;

                case UI_TYPE_INFORMATION:
                    UIInformation.setVisibility(View.VISIBLE);
                    currentUIType=UIType.UI_TYPE_INFORMATION;

                    break;


                default:
                    break;
            }
        }
    }

}
