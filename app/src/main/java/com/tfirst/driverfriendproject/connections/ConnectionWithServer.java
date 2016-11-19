package com.tfirst.driverfriendproject.connections;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Stanislav Trushin on 19.11.2016.
 */

public class ConnectionWithServer extends AsyncTask<String, Void, String> {
    private boolean isRunning = true;
    public String result;

    @Override
    //protected String doInBackground(Connection... connection) {
    protected String doInBackground(String... strings) {
        String line = null;
        int serverPort = 3333; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "10.212.1.186"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        try {
            while (this.isRunning) {
                InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
                System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
                Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
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

                    line =  dataInputStream.readUTF();

                    System.out.println("The server was very polite. It sent me this : " + line);
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
        this.result = line;
    }
}
