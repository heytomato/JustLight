package sz.heytomato.justlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/5/7.
 */
public class WarningLight extends Flashlight {
    protected boolean warningLightFlicker;//true：闪烁 false：停止闪烁
    protected boolean warnjingLightState;//true：on—off false：off—on


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        warningLightFlicker=true;

    }

    class WarningLightThread extends Thread{
        public void run(){
            warningLightFlicker=true;
            while (warningLightFlicker){
                try{
                    Thread.sleep(currentWarningLightInterval);
                    warningHandler.sendEmptyMessage(0);
                }catch (Exception e){
                    //TODO:handle exception
                }
            }
        }
    }
    private Handler warningHandler=new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (warnjingLightState) {
                imageViewWarningLight1.setImageResource(R.drawable.warning_light_on);
                imageViewWarningLight2.setImageResource(R.drawable.warning_light_off);
                warnjingLightState = false;

            }else {
                imageViewWarningLight1.setImageResource(R.drawable.warning_light_off);
                imageViewWarningLight2.setImageResource(R.drawable.warning_light_on);
                warnjingLightState = true;
            }
        }
    };
}
