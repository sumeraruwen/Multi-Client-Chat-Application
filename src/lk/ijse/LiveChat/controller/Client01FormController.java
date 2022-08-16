package lk.ijse.LiveChat.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client01FormController {
    public TextArea textArea;
    public TextField textMessage;
    public AnchorPane emojiBox;

    int PORT = 5000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message = "";

    public void initialize(){
        emojiBox.setVisible(false);
        new Thread(() -> {
            try {
                socket = new Socket("localhost",PORT);

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    //textArea.appendText("\n Client01 : " + message);
                    textArea.appendText("\n" + message);

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

    public void emojiOnAction(MouseEvent mouseEvent) {
        emojiBox.setVisible(true);
    }

    public void emojiExit(MouseEvent mouseEvent) {
        emojiBox.setVisible(false);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("My Files");
        File filePath = fileChooser.showOpenDialog(new Stage());

       /* ObjectOutputStream dataOutputStreamImg = new ObjectOutputStream(socket.getOutputStream());
        textArea.appendText(String.valueOf(filePath.getAbsoluteFile()));
        dataOutputStreamImg.flush();*/
    }

    public void emoji1(MouseEvent mouseEvent) throws IOException {
      textMessage.appendText("\uD83D\uDE02");
    }

    public void emoji2(MouseEvent mouseEvent) {
        textMessage.appendText("\uD83D\uDE97");
    }
}
