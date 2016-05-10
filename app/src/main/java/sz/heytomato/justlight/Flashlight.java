package sz.heytomato.justlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/5/6.
 */
public class Flashlight extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageViewFlashlight.setTag(false);



        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)getImageViewFlashlightController.getLayoutParams();
        layoutParams.height=point.y*3/4;
        layoutParams.width=point.x/3;

        getImageViewFlashlightController.setLayoutParams(layoutParams);


    }

    public void onClick_Flashlight(View view){
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            Toast.makeText(this,"当前设备没有闪光灯",Toast.LENGTH_LONG).show();
            return;
        }
        if (((Boolean)imageViewFlashlight.getTag())==false){
            openFlashlight();
        }
        else {
            closeFlashlight();
        }
    }

    //打开闪光灯
    protected void openFlashlight(){
        TransitionDrawable drawable=(TransitionDrawable)imageViewFlashlight.getDrawable();
        drawable.startTransition(25);
        imageViewFlashlight.setTag(true);


        try{
            camera= Camera.open();
            int textureId=0;
            camera.setPreviewTexture(new SurfaceTexture(textureId));
            camera.startPreview();
            parameters=camera.getParameters();
            parameters.setFlashMode(parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);


        }catch (Exception e){
            //TODO:handle exception
        }
    }

    //关闭闪光灯
    public void closeFlashlight(){
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
    }
    protected void onPause(){
        super.onPause();
        closeFlashlight();
    }
}
