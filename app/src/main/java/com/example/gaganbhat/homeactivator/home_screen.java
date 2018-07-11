package com.example.gaganbhat.homeactivator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class home_screen extends Activity {

    private static String IP_ADDRESS = "192.168.1.11";
    private static int PORT = 5005;

    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        TextView textViewCustom = (TextView) findViewById(R.id.textView);
        Typeface bebasNeue = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Thin.otf");
        textViewCustom.setTypeface(bebasNeue);

        final Switch relaySwitch = (Switch) findViewById(R.id.switchRelay);
//
//        Handler handler = new Handler();
//
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                if (relaySwitch.isChecked()) {
//                    try {
//                        sendPacket("EnableRelay");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    try {
//                        sendPacket("DisableRelay");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };

        }

        private void sendPacket(String str) throws IOException{
            OutputStreamWriter osw;
            try {
                socket = new Socket(IP_ADDRESS, PORT);
                osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                osw.write(str, 0, str.length());
            } catch (IOException e) {
                System.err.print(e);
            } finally {
                socket.close();
            }
        }

}
