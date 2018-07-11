package com.example.gaganbhat.homeactivator;

import android.app.Activity;
import android.graphics.Typeface;
import android.location.OnNmeaMessageListener;
import android.os.Handler;
import android.os.StrictMode;
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

    TcpClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tcpClient = new TcpClient();
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
            case R.id.btnEnableRelay :
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            tcpClient.sendToPort("EnableRelay");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
//                new SendEnable().execute();
        }
    }





    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (relaySwitch.isChecked()) {
                Toast.makeText(getApplicationContext(), "CHECKED",Toast.LENGTH_SHORT).show();
//                try {
//                    sendToPort("EnableRelay");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    sendToPort("DisableRelay");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    };


}
