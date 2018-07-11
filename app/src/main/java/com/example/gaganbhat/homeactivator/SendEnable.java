package com.example.gaganbhat.homeactivator;

import android.os.AsyncTask;

public class SendEnable extends AsyncTask{

    TcpClient tcpClient;

    protected void doInBackground() {
        tcpClient = new TcpClient();
        try{
            tcpClient.run();
            tcpClient.sendMessage("asda");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }
}
