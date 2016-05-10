package sz.heytomato.justlight;

import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by Heytomato on 2016/5/6.
 */
public class BaseActivity extends AppCompatActivity {

    protected enum UIType{
        UI_TYPE_MAIN,UI_TYPE_FLASHLIGHT,UI_TYPE_WARNINGLIGHT,UI_TYPE_SCRNLIGHT,UI_TYPE_COLORFULLIGHT,UI_TYPE_INFORMATION
    }

    protected int currentWarningLightInterval =100;
    protected int currentColorfulLightInterval=100;

    protected ImageView imageViewFlashlight;
    protected ImageView getImageViewFlashlightController;
    protected ImageView imageViewWarningLight1;
    protected ImageView imageViewWarningLight2;

    protected SeekBar seekBarWarningLight;
    protected SeekBar seekBarColorfulLight;


    protected Camera camera;
    protected Camera.Parameters parameters;

    protected FrameLayout UIFlashlight;
    protected LinearLayout UIMain;
    protected LinearLayout UIWarningLight;
    protected RelativeLayout UIScrnLight;
    protected FrameLayout UIColorLight;
    protected LinearLayout UIInformation;

    protected UIType currentUIType=UIType.UI_TYPE_FLASHLIGHT;
    protected UIType lastUIType=UIType.UI_TYPE_FLASHLIGHT;

    protected int defoultScreenBrightness;

    protected int finishCount=0;

    protected TransitionDrawable drawable;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIFlashlight=(FrameLayout)findViewById(R.id.framelayout_flashlight);
        UIMain=(LinearLayout)findViewById(R.id.linearlayout_main);
        UIWarningLight=(LinearLayout)findViewById(R.id.linearlayout_warning_light);
        UIScrnLight=(RelativeLayout)findViewById(R.id.relativelayout_scrn_light);
        UIColorLight=(FrameLayout)findViewById(R.id.framelayout_colorful_light);
        UIInformation=(LinearLayout)findViewById(R.id.linearlayout_information);

        imageViewFlashlight=(ImageView)findViewById(R.id.imageview_flashlight);
        getImageViewFlashlightController=(ImageView)findViewById(R.id.imageview_flashlight_controller);
        imageViewWarningLight1=(ImageView)findViewById(R.id.imageview_warning_light1);
        imageViewWarningLight2=(ImageView)findViewById(R.id.imageview_warning_light2);
        seekBarColorfulLight=(SeekBar)findViewById(R.id.seekbar_colorful_light);
        seekBarWarningLight=(SeekBar)findViewById(R.id.seekbar_warning_light);



        defoultScreenBrightness=getScreenBrightness();

        seekBarWarningLight.setProgress(currentWarningLightInterval-100);
        seekBarColorfulLight.setProgress(currentColorfulLightInterval-100);
    }

    protected void hideAllUI(){
        UIMain.setVisibility(View.GONE);
        UIFlashlight.setVisibility(View.GONE);
        UIWarningLight.setVisibility(View.GONE);
        UIScrnLight.setVisibility(View.GONE);
        UIColorLight.setVisibility(View.GONE);
        UIInformation.setVisibility(View.GONE);
    }

    protected void screenBrightness(float value){
        try{
            WindowManager.LayoutParams layoutParams=getWindow().getAttributes();
            layoutParams.screenBrightness=value;
            getWindow().setAttributes(layoutParams);
        }catch (Exception e){
            //TODO: handle exception
        }
    }

    protected int getScreenBrightness(){
        int value=0;
        try{
            value=android.provider.Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        }catch (Exception e){
            //TODO:handle exception
        }
        return value;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent){
        finishCount=0;
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override
    public void finish(){
        finishCount++;
        if (finishCount==1){
            Toast.makeText(this,"再按一次返回键退出！",Toast.LENGTH_LONG).show();
        }else if (finishCount==2){
            super.finish();
        }
    }
}
