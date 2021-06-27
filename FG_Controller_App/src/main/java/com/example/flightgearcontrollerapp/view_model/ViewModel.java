package com.example.flightgearcontrollerapp.view_model;

import android.view.KeyEvent;
import android.widget.ImageButton;

import com.example.flightgearcontrollerapp.model.FlightGearPlayer;

public class ViewModel {

    private FlightGearPlayer FGModel;

    public ViewModel() {
        this.FGModel = new FlightGearPlayer();
    }

    public void connectClicked(String ip, int port) { this.FGModel.connect(ip, port);}
    public void onRudderChange(double value)
        {FGModel.changeRudder((double) (value - 50) / 100.0);}
    public void onAileronChange(double value) {
        value = (double) ((int)(value * 100)) / 100.0;
        FGModel.changeAileron(value);
    }
    public void onElevatorChange(double value) {
        value = (double) ((int)(value * 100)) / 100.0;
        FGModel.changeElevator(-value);
    }
    public void onThrottleChange(int value) {FGModel.changeThrottle((double) value / 100.0);}
}
