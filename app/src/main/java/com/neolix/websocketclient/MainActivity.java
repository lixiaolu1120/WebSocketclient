package com.neolix.websocketclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends Activity {

    private EditText ipAddress;
    private Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = (EditText) findViewById(R.id.ip_address);
        connect = (Button) findViewById(R.id.connect_bt);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InetAddress serverAddr = null;//TCPServer.SERVERIP
                try {
                    serverAddr = InetAddress.getByName("192.168.0.149");
                    Socket socket = new Socket(serverAddr, 51706);
                    String message = "AndroidRes,Where is my Pig (Android)?";
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    Log.i("lixiaolu", "Socket Output :" + out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }
}
