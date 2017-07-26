package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cgavlabs.jeepforecast.BaseDialogFragment;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import static com.cgavlabs.jeepforecast.Constants.SELECT_PICTURE;


abstract class BaseWeatherConfigFragment extends BaseDialogFragment {
    static final String REGEX_IS_NUMBER = "^[0-9]*$";

    View dialogView;
    ImageView jeepImg;
    EditText lowTempET;
    EditText highTempET;
    EditText lowPrecipET;
    EditText highPrecipET;

    private NewWeatherConfigFragment.NewWeatherConfigDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = ((NewWeatherConfigFragment.NewWeatherConfigDialogListener) context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_weather_config, null);
        jeepImg = (ImageView) dialogView.findViewById(R.id.config_add_image);
        jeepImg.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        });
        lowTempET = (EditText) dialogView.findViewById(R.id.config_temp_low);
        highTempET = (EditText) dialogView.findViewById(R.id.config_temp_high);
        lowPrecipET = (EditText) dialogView.findViewById(R.id.config_precip_low);
        highPrecipET = (EditText) dialogView.findViewById(R.id.config_precip_high);
        return super.onCreateDialog(savedInstanceState);
    }

    AlertDialog buildDialog(Integer id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(dialogView)
                .setPositiveButton("SAVE", (dialogInterface, i) -> {
                    Integer lt = Integer.valueOf(lowTempET.getText().toString());
                    Integer ht = Integer.valueOf(highTempET.getText().toString());
                    Integer lp = Integer.valueOf(lowPrecipET.getText().toString());
                    Integer hp = Integer.valueOf(highPrecipET.getText().toString());
                    String imgUri = (String) jeepImg.getTag();
                    WeatherConfig wc = new WeatherConfig(id, lt, ht, lp, hp, imgUri);
                    listener.onAddNewWeatherConfig(wc);
                })
                .setNegativeButton("CANCEL", (dialog, i) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(
                dialog1 -> ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE)
                        .setEnabled(false));
        dialog.show();
        return dialog;
    }

}
