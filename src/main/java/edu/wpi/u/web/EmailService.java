package edu.wpi.u.web;

import io.github.cdimascio.dotenv.Dotenv;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EmailService {

//    private static Dotenv dotenv = Dotenv.load();
    public static void main(String[] args) {
        //sendMail("nev.ingram30@gmail.com","The return of testy westy");
    }
    public void sendMail(String to, String body){ // todo : take in a SpecificRequest
//        Email email = EmailBuilder.startingBlank()
//                .from("Brigham & Women's Service Request Notifier", "cs3733teamu@gmail.com")
//                .to("", to)
//                .withSubject("New Service Request") // todo : replace with type
//                .withPlainText(body) // todo : formatting for more request options
//                .buildEmail();
//        MailerBuilder
//                .withSMTPServer("smtp.gmail.com", 25, "cs3733teamu@gmail.com", dotenv.get("EMAIL_PASSWORD"))
//                .withTransportStrategy(TransportStrategy.SMTP_TLS)
//                .buildMailer()
//                .sendMail(email); todo : put in try catch
    }
}