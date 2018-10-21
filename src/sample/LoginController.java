package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;

public class LoginController {

    @FXML
    TextField mailText;
    @FXML
    PasswordField passText;

    public static String fromEmail=null;
    public static String password=null;
    public void mailSend (MouseEvent e) throws IOException {

        fromEmail=mailText.getText();
        password=passText.getText();
        if(fromEmail.length()>0 && password.length()>0){
            Controller.changeScene(e,"sample.fxml");
            System.out.println(fromEmail+" "+password);
        }else{
            JOptionPane.showMessageDialog(null , "Lütfen boş alan bırakmayınız.");
        }
    }
}
