package com.example.Ex6Demo_client;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientThread implements Runnable{
    DatagramSocket remoteDs=null;
    InetAddress remoteAddr=null;
    private final static int remotePort=5000;
    byte[] buf=new byte[1024];
    public static Handler sendHandler;
    public ClientThread(){
        try {
            remoteDs=new DatagramSocket();
            remoteAddr= InetAddress.getByName("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        new Thread(){
        @Override
        public void run() {
            Looper.prepare();
            sendHandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what==0x1234){
                        byte[] buffer=msg.getData().getString("sendContent").getBytes();
                        DatagramPacket remoteDp=new DatagramPacket(buffer,buffer.length,remoteAddr,remotePort);
                        try {
                            remoteDs.send(remoteDp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        }
        }.start();
    }
}
