package com.example.flightgearcontrollerapp.views;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flightgearcontrollerapp.R;
import com.example.flightgearcontrollerapp.model.FlightGearPlayer;
import com.example.flightgearcontrollerapp.view_model.ViewModel;

public class MainActivity extends AppCompatActivity {

    ViewModel vm;
    Joystick joystickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Creating our view model.
        vm = new ViewModel();

        createSeekBarListeners();
        this.joystickView = findViewById(R.id.joystickView);
        this.joystickView.joystickCallback = new Joystick.JoystickListener() {
            @Override
            public void onJoystickMoved(double xPercent, double yPercent, int source) {
                // View notifies VM of Aileron and Elevator changes.
                vm.onAileronChange(xPercent);
                vm.onElevatorChange(yPercent);
            }
        };

        // Some color and graphics setting for better appeal.
        initViewColors();
    }

    public void createSeekBarListeners() {
        SeekBar rudder_seekBar = (SeekBar) findViewById(R.id.rudder_bar);
        rudder_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // View is command the Model view
                vm.onRudderChange(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar throttle_seekBar = (SeekBar) findViewById(R.id.throttle_bar);
        throttle_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // View is command the Model view
                vm.onThrottleChange(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No use.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No use.
            }
        });
    }

    public void onConnectClick(View view) {

        FlightGearPlayer fg = new FlightGearPlayer();
        EditText ip_address = (EditText) this.findViewById(R.id.IP_Address);
        EditText server_port = (EditText) this.findViewById(R.id.Server_Port);
        try {
            int port = Integer.parseInt(server_port.getText().toString());
            vm.connectClicked(ip_address.getText().toString(), port);
            Toast connectionMessage =
                    Toast.makeText(
                            getApplicationContext(), "Connection Success!", Toast.LENGTH_LONG);
            connectionMessage.show();
        } catch (Exception e) {
            Toast port = Toast.makeText(
                    getApplicationContext(), "Connection Failed!", Toast.LENGTH_LONG);
            port.show();
        }

    }

    public void initViewColors() {
        // This will be the "Connect" text color.
        TextView connectText = findViewById(R.id.Connect_TextView);
        int connectTransparency = 220, connectRed = 20, connectGreen = 200, connectBlue = 80;
        connectText.setTextColor(
                Color.argb(connectTransparency, connectRed, connectGreen, connectBlue));


        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setTitle("FlightGear Controller App");
        // Define ColorDrawable object and parse color, using parseColor method
        String actionBarColor = "#FF0A1341";    // Dark Blue
//        String actionBarColor = "#FF19960B"; // Healthy Grass
//        String actionBarColor = "#FF0F4D38"; // Dark Cyan
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(actionBarColor));
        actionBar.setBackgroundDrawable(colorDrawable);
    }
}