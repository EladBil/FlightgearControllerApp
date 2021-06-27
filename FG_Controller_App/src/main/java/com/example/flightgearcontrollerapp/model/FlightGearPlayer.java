package com.example.flightgearcontrollerapp.model;

import java.io.PrintWriter;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// This is the Model in our MVVM design pattern.
public class FlightGearPlayer {

    private final String COMMAND_AILERON = "set /controls/flight/aileron ";
    private final String COMMAND_ELEVATOR = "set /controls/flight/elevator ";
    private final String COMMAND_RUDDER = "set /controls/flight/rudder ";
    private final String COMMAND_THROTTLE = "set /controls/engines/current-engine/throttle ";



    // To this one threaded thread pool we will inject the commands to FG.
    ExecutorService executor;
    Socket fgSocket;
    PrintWriter outputPrinterForFG;

    public FlightGearPlayer() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void connect(String ip, int port) {
        executor.execute(new RunnableFGConnect(ip, port, this.fgSocket));
    }

    public void changeAileron(double value) {
        executor.execute(new RunnableFGChangeOption(value, COMMAND_AILERON));
    }

    public void changeRudder(double value) {
        executor.execute(new RunnableFGChangeOption(value, COMMAND_RUDDER));
    }

    public void changeElevator(double value) {
        executor.execute(new RunnableFGChangeOption(value, COMMAND_ELEVATOR));
    }

    public void changeThrottle(double value) {
        executor.execute(new RunnableFGChangeOption(value, COMMAND_THROTTLE));
    }


    /*
     * This Class is the task that will be responsible on connecting to flightgear;
     *
     */
    private class RunnableFGConnect implements Runnable {

        String ip;
        int port;
        Socket flightGearSocket;

        public RunnableFGConnect(String ip, int port, Socket flightGear) {
            this.ip = ip;
            this.port = port;
            this.flightGearSocket = flightGear;
        }

        /*
         * This method will connect the device with Flight Gear with the given IP,Port.
         * We will open another thread to manage all the communication with FG,
         * by using it as an Active Object (Implemented as a ONE thread Thread Pool).
         */
        @Override
        public void run() {
            try {
                this.flightGearSocket = new Socket(ip, port);
                outputPrinterForFG =
                        new PrintWriter(flightGearSocket.getOutputStream(), true);
            } catch(Exception e) {
//                System.out.println(e.toString());
//                System.out.println(("Failed to connect FG\n"));
//                System.out.flush();
                return;
            }
//            System.out.println(("Connected to FG"));
        }
    }


    private class RunnableFGChangeOption implements Runnable {


        private final double value;
//        private final String printFormat = "set /controls/engines/current-engine/throttle ";
        private final String printFormat;

        public RunnableFGChangeOption(double value, String command) {
            this.value = value;
            this.printFormat = command;
        }

        @Override
        public void run() {
            if (outputPrinterForFG == null) {
                System.out.println("Connect to FG First!");
            } else {
                System.out.println(printFormat + value + "\n");
                outputPrinterForFG.print(printFormat + value + "\r\n");
                outputPrinterForFG.flush();
            }
        }
    }

}

