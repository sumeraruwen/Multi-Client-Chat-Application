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

public class Client03FormController {
    public TextArea textArea;
    public TextField textMessage;
    public AnchorPane emojiBox;

    int PORT3 = 49155;
    Socket socket3;
    DataInputStream dataInputStream3;
    DataOutputStream dataOutputStream3;

    String message3 = "";

    public void initialize(){
        emojiBox.setVisible(false);
        new Thread(() -> {
            try {
                socket3 = new Socket("localhost",PORT3);

                dataInputStream3 = new DataInputStream(socket3.getInputStream());
                dataOutputStream3 = new DataOutputStream(socket3.getOutputStream());

                while (!message3.equals("exit")){
                    message3 = dataInputStream3.readUTF();
                    // textArea.appendText("\n Client02 : " + message2);
                    textArea.appendText("\n  " + message3);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream3.writeUTF(textMessage.getText().trim());
        dataOutputStream3.flush();
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
