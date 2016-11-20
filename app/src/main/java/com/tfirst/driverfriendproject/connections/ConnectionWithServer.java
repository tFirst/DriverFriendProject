package com.tfirst.driverfriendproject.connections;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.tfirst.driverfriendproject.MainMenuActivity;
import com.tfirst.driverfriendproject.events.SendInformationActivity;
import com.tfirst.driverfriendproject.gethelp.GetHelp;
import com.tfirst.driverfriendproject.map.GeneralMapActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionWithServer extends AsyncTask<String, Void, String> {
    private boolean isRunning = true;
    private Activity activity;
    private SendInformationActivity sia = null;
    private GeneralMapActivity gma = null;
    private GetHelp gh = null;

    public ConnectionWithServer(SendInformationActivity sia) {
        this.sia = sia;
    }

    public ConnectionWithServer(GeneralMapActivity gma) {
        this.gma = gma;
    }

    public ConnectionWithServer(GetHelp gh) {
        this.gh = gh;
    }

    @Override
    protected String doInBackground(String... strings) {
        String line = null;
        int serverPort = 3333; // port
        String address = "10.212.2.246"; // server's ip-address
        try {
            while (this.isRunning) {
                InetAddress ipAddress = InetAddress.getByName(address);
                System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
                Socket socket = new Socket(ipAddress, serverPort);
                System.out.println("Yes! I just got hold of the program.");

                InputStream inputStream = socket.getInputStream();
                System.out.println("Started input stream...");
                OutputStream outputStream = socket.getOutputStream();
                System.out.println("Started output stream...");

                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
                System.out.println();

                while (true) {
                    System.out.println("Sending this line to the server...");
                    dataOutputStream.writeUTF(strings[0]);
                    dataOutputStream.flush();

                    line = dataInputStream.readUTF();

                    System.out.println("The server was very polite. It sent me this: " + line);
                    System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                    break;
                }
                break;
            }
            this.isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }

    protected void onPostExecute(String line) {
        if(gma != null){
            gma.setResult(line);
        } else if(gh != null) {
            System.out.println("it is gh");
        } else {
            System.out.println("it is sia");
        }
    }

}
