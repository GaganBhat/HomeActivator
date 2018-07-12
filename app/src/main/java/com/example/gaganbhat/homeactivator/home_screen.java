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
    Button btnDisableRelay;

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
        btnDisableRelay = (Button) findViewById(R.id.btnDisableRelay);

        btnEnableRelay.setOnClickListener(this);
        btnDisableRelay.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEnableRelay :
                Thread enableThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            tcpClient.sendToPort("EnableRelay");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                enableThread.start();
                break;

            case R.id.btnDisableRelay :
                Thread disableThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try  {
                            tcpClient.sendToPort("Disable");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                disableThread.start();
//                new SendEnable().execute();
        }
    }


}
