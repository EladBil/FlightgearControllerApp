# FlightgearControllerApp

Hi welcome to our FlightGear Controller Android app.

<ins>About this app:</ins><br/>
  This is will serve you as an android controller for FlightGear flight simulator. <br/>
  From the app you will be able to control the plane throttle, aileron, rudder and everything you
  need for it to move.

<ins>Link to Youtube:</ins><br/>
  https://youtu.be/IaizChBFdbc<br/>
<br/>
NOTICE: You can see the Class Diagram .pdf file in our project files.<br/>
<ins>Guide to user:</ins><br/>
  As the app isn't yet on the android app store you will need to have Android Studio in order to download the app.<br/>
  1. Download Android Studio: <br/>
    Link: https://developer.android.com/studio<br/>
  2. Download our project from git and open it in android studio.<br/>
  3. Connect your android device to the computer via cable.<br/>
  4. In order to Anroid Studio to recognize your device you need to:<br/>
    A. Enable Developer Options on your device. Tutorial: https://developer.android.com/studio/debug/dev-options<br/>
    B. In the Developer Options, enable USB Debugging.<br/>
    Android Studio should be able to recognize your device now.<br/>
  5. Run (and Build) the project on android studio and the app will be loaded to your device.<br/>
    Notice: You can't connect (yet) to Flightear!<br/>
  6. Download FlightGear (If you havn't already) Link:https://www.flightgear.org/download/<br/>
  7. Open FlightGear and select settings.
  8. Enter in Additional Settings this line here: --telnet=socket,in,10,127.0.0.1,/*Enter your selected port*/,tcp<br/>
    In the port section enter the port you want the FlightGear server to run on. We recommend: 6400.<br/>
  9. You are done with the setup!<br/>
  10. Now you can run your app and enter your pc IP and the port you selected.<br/>
    Notice: the IP you enter in app can be found by (On Windows only, other OS's have different ways.) running ipconfig on CMD.<br/>
  11. Have Fun!<br/>
<br/>
<ins>Guide to developer:</ins><br/>
  1. Do the same steps in ther user guide in order to run the app, and get ahold of the files.<br/>
  2. By opening Android Studio and going to FG_Controller_App you can see all the Android files you need.<br/>
  TIPS for Developer:<br/>
    A. We strongly recommend load the app to the phone and run the app from there instead of the Emulator Android Studio offers.<br/>
      It is better for performence of your computer as FlightGear and Android Studio already heavy resources apps.<br/>
    B. We used Java as our programming language but Android Studio have the option to convert the project to Kotlin, if you wish to<br/> 
      
  
