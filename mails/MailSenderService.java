package currency.pick.kg.mails;

public interface MailSenderService {

    void sendEmail(String to, String subject, String body);
}
