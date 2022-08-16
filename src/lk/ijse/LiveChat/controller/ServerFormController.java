package lk.ijse.LiveChat.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController{
    public TextArea textArea;
    public TextField textMessage;

     int PORT = 5000;
     int PORT2 = 8000;
     int PORT3 = 49155;

    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    ServerSocket serverSocket2;
    Socket accept2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;

    ServerSocket serverSocket3;
    Socket accept3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;


    String message = "";
    String message2 = "";
    String message3 = "";

    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                //serverSocket = new ServerSocket(PORT2);
                textArea.appendText("Server Started..");
                accept = serverSocket.accept();
                textArea.appendText("\nSumera Connected..");
                Thread thread = new Thread();
                thread.start();

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nSumera : " + message);

                    dataOutputStream.writeUTF(message.trim());
                    dataOutputStream.flush();

                    dataOutputStream2.writeUTF("Sumera : "+message.trim());
                    dataOutputStream2.flush();

                    dataOutputStream3.writeUTF("Sumera : "+message.trim());
                    dataOutputStream3.flush();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                serverSocket2 = new ServerSocket(PORT2);
                accept2 = serverSocket2.accept();
                textArea.appendText("\nSupipi Connected..");

                dataInputStream2 = new DataInputStream(accept2.getInputStream());
                dataOutputStream2 = new DataOutputStream(accept2.getOutputStream());

                while (!message2.equals("exit")){
                    message2 = dataInputStream2.readUTF();
                    textArea.appendText("\nSupipi : " + message2);

                    dataOutputStream2.writeUTF(message2.trim());
                    dataOutputStream2.flush();

                    dataOutputStream.writeUTF("Supipi : "+message2.trim());
                    dataOutputStream.flush();

                    dataOutputStream3.writeUTF("Supipi : "+message2.trim());
                    dataOutputStream3.flush();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }).start();

        new Thread(() -> {
            try {
                serverSocket3 = new ServerSocket(PORT3);
                accept3 = serverSocket3.accept();
                textArea.appendText("\nViki Connected..");

                dataInputStream3 = new DataInputStream(accept3.getInputStream());
                dataOutputStream3 = new DataOutputStream(accept3.getOutputStream());

                while (!message3.equals("exit")){
                    message3 = dataInputStream3.readUTF();
                    textArea.appendText("\nViki : " + message3);

                    dataOutputStream3.writeUTF(message3.trim());
                    dataOutputStream3.flush();

                    dataOutputStream.writeUTF("Viki : "+message3.trim());
                    dataOutputStream.flush();

                    dataOutputStream2.writeUTF("Viki : "+message3.trim());
                    dataOutputStream2.flush();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }).start();

    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(textMessage.getText().trim());
        dataOutputStream.flush();
    }


}
