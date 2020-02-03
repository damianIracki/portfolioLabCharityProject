package pl.coderslab.charity.mailSander;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
