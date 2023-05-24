package currency.pick.kg.configs;


import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
public class RestConfiguration {

    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private int port;

    @Value("${email.sender}")
    private String senderEmail;

    @Value("${email.pass}")
    private String senderPass;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(senderEmail);
        mailSender.setPassword(senderPass);

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
