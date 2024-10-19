package com.securehaven.securehaven_msclient_customer_login.emailutility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.securehaven.securehaven_msclient_customer_login.model.Customer_Login;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@Component
public class Customer_Login_Email_Sender {
	
	@Autowired
     JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	String logo="<html><body>"
			+"<p style='color:#ff0000;'>Secure Haven</p>"
			+"</body></html>";

    public void send_Customer_Registration_Email(Customer_Login customer) {
    	log.info("Email method Started");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setFrom(fromEmail);
        message.setSubject("Welcome "+customer.getFirstName()+" to Secure Haven!");
        message.setText("Dear " + customer.getFirstName() + ",\n\n"
                + "Welcome to Secure Haven Thank you for registering with us.\n\n"
                + "Your username is: " + customer.getUsername() + "\n"
                + "Please keep your username and password secure.\n\n"
                + "If you have any questions, feel free to contact us.\n\n"
                + "Best regards,\n"
                + "Secure Haven Team");
       
            emailSender.send(message);
            log.info("Email method ended");
        
    }

}
