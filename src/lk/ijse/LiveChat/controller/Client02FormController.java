package lk.ijse.LiveChat.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client02FormController {
    public TextArea textArea;
    public TextField textMessage;
    public AnchorPane emojiBox;

    int PORT2 = 8000;
    Socket socket2;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream2;

    String message2 = "";

    public void initialize(){
        emojiBox.setVisible(false);
        new Thread(() -> {
            try {
                socket2 = new Socket("localhost",PORT2);

                dataInputStream2 = new DataInputStream(socket2.getInputStream());
                dataOutputStream2 = new DataOutputStream(socket2.getOutputStream());

                while (!message2.equals("exit")){
                    message2 = dataInputStream2.readUTF();
                   // textArea.appendText("\n Client02 : " + message2);
                   textArea.appendText("\n  " + message2);


                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream2.writeUTF(textMessage.getText().trim());
        dataOutputStream2.flush();
    }

    public void emojiOnAction(MouseEvent mouseEvent) {
        emojiBox.setVisible(true);
    }

    public void emojiExit(MouseEvent mouseEvent) {
        emojiBox.setVisible(false);
    }

    public void imageSendOnAction(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("My Files");
        fileChooser.showOpenDialog(new Stage());
    }

    public void emoji1(MouseEvent mouseEvent) {
        textMessage.appendText("\uD83D\uDE02");
    }

    public void emoji2(MouseEvent mouseEvent) {
        textMessage.appendText("\uD83D\uDE97");
    }
}
