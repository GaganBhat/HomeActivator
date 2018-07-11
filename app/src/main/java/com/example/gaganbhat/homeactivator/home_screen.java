package com.example.gaganbhat.homeactivator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class home_screen extends Activity implements View.OnClickListener{

    private static String IP_ADDRESS = "192.168.1.11";
    private static int PORT = 5005;

    Socket socket;
    Switch relaySwitch;
    Button btnEnableRelay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            sendToPort("EnableRelay");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        TextView textViewCustom = (TextView) findViewById(R.id.textView);
        Typeface bebasNeue = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Thin.otf");
        textViewCustom.setTypeface(bebasNeue);

        relaySwitch = (Switch) findViewById(R.id.switchRelay);
        btnEnableRelay = (Button) findViewById(R.id.btnEnableRelay);

        btnEnableRelay.setOnClickListener(this);

        Handler handler = new Handler();
        handler.postDelayed(r, 500);

        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEnableRelay:
                try {
                    sendToPort("EnableRelay");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void sendToPort(String str) throws IOException {
        Socket socket = null;
        OutputStreamWriter osw;
        System.out.println("RAN-1");
        try {
            socket = new Socket("192.168.1.11", 5005);
            osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            System.out.println(str);
            osw.write(str, 0, str.length());
            osw.flush();
            System.out.println("RAN-2");
        } catch (IOException e) {
            System.err.print(e);
        } finally {
            System.out.println("RAN-3");
            if (socket != null) {
                socket.close();
            }
        }

    }


    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (relaySwitch.isChecked()) {
                Toast.makeText(getApplicationContext(), "CHECKED",Toast.LENGTH_SHORT);
                try {
                    sendToPort("EnableRelay");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sendToPort("DisableRelay");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };


}
