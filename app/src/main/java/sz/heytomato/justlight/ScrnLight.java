package sz.heytomato.justlight;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ScrnLight extends WarningLight {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    public void onClick_ColorRed(View view){
        UIScrnLight.setBackgroundResource(R.color.pred);
    }
    public void onClick_ColorYellow(View view){
        UIScrnLight.setBackgroundResource(R.color.pyellow);
    }
    public void onClick_ColorBule(View view){
        UIScrnLight.setBackgroundResource(R.color.pbule);
    }
    public void onClick_ColorWhite(View view){
        UIScrnLight.setBackgroundResource(R.color.pwhite);
    }
    public void onClick_ColorGreen(View view){
        UIScrnLight.setBackgroundResource(R.color.pgreen);
    }


}
