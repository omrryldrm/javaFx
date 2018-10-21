package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


public class Controller {


    @FXML
    TextField alıcıText;
    @FXML
    TextField subText;
    @FXML
    TextArea mesText;
    @FXML
    Button sendBtn;

    public static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "E-mail Sender"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Mesaj hazır..");
            Transport.send(msg);

            System.out.println("EMail Başarıyla Gönderildi!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sender() {
        String fromEmail=LoginController.fromEmail;
        String password=LoginController.password;
        String toEmail = alıcıText.getText();

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail,password);
            }
        };
        Session session = Session.getInstance(props, auth);

        sendEmail(session, toEmail,subText.getText(), mesText.getText());
        alıcıText.setText("");
        subText.setText("");
        mesText.setText("");

    }

    public  static void changeScene(MouseEvent e, String fxml) throws IOException {
        Parent newScene=FXMLLoader.load(Controller.class.getResource(fxml));
        Scene loginScene=new Scene(newScene);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();


        window.setScene(loginScene);
        window.show();

    }



}


