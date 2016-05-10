package sz.heytomato.justlight;

import android.os.Bundle;
import android.widget.SeekBar;

/**
 * Created by Administrator on 2016/5/7.
 */
public class Information extends ColorfulLight implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        seekBarColorfulLight.setOnSeekBarChangeListener(this);
        seekBarWarningLight.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
        switch (seekBar.getId()){
            case R.id.seekbar_warning_light:
                currentWarningLightInterval=progress+100;
                break;
            case R.id.seekbar_colorful_light:
                currentColorfulLightInterval=progress+50;
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }




}
