package sz.heytomato.justlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ColorfulLight extends ScrnLight {
    protected boolean colorState;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    private void sleepExt(long t){
        try{
            Thread.sleep(t);
        }catch (Exception e){
            //TODO:handle exception
        }
    }


    class ColorfulThread extends Thread{


        public void run(){
            colorState=true;
            while (colorState){
                handler.sendEmptyMessage(Color.BLUE);
                sleepExt(currentColorfulLightInterval);
                handler.sendEmptyMessage(Color.BLACK);
                sleepExt(currentColorfulLightInterval);
                handler.sendEmptyMessage(Color.RED);
                sleepExt(currentColorfulLightInterval);
                handler.sendEmptyMessage(Color.BLACK);
                sleepExt(currentColorfulLightInterval);

            }
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message message){
            int color=message.what;
            UIColorLight.setBackgroundColor(color);
        }
    };
}
