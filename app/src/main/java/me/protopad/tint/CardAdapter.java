package me.protopad.tint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;
import com.philips.lighting.quickstart.R;


import java.util.List;

/**
 * Created by cbrooks on 10/23/14.
 */
public class CardAdapter extends ArrayAdapter<PHLight> {
    private final Context context;
    private final List<PHLight> lights;
    private final PHHueSDK phHueSDK;

    public CardAdapter(Context context, int resource, List<PHLight> lights, PHHueSDK phHueSDK) {
        super(context, resource, lights);
        this.context = context;
        this.lights = lights;
        this.phHueSDK = phHueSDK;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final PHLight light = lights.get(position);
        View cardView = inflater.inflate(R.layout.light_card, parent, false);
        TextView textView = (TextView) cardView.findViewById(R.id.light_name);
        textView.setText(lights.get(position).getName());

        PHLightState state = light.getLastKnownLightState();

        Switch swtch = (Switch) cardView.findViewById(R.id.switch1);
        swtch.setChecked(light.getLastKnownLightState().isOn());
        swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PHBridge bridge = phHueSDK.getSelectedBridge();
                PHLightState lightState = new PHLightState();
                lightState.setOn(isChecked);
                // To validate your lightstate is valid (before sending to the bridge) you can use:
                // String validState = lightState.validateState();
                bridge.updateLightState(light, lightState, null);
            }
        });

        SeekBar seekBar = (SeekBar) cardView.findViewById(R.id.seekBar);
        seekBar.setMax(255);
        seekBar.setProgress(state.getBrightness());
        //seekBar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(Color.parseColor(state.getColorMode()), PorterDuff.Mode.MULTIPLY));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                PHBridge bridge = phHueSDK.getSelectedBridge();
                PHLightState lightState = new PHLightState();
                lightState.setBrightness(progress);
                //if(lightState.validateState().equals(""))

                bridge.updateLightState(light, lightState);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return cardView;
    }

}
