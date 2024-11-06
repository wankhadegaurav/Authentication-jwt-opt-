package in.authentication.Otpauthentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
    @Autowired
    private JavaMailSender javaMailSender;



    public void sendemailotp(String email,String opt)
    {
           SimpleMailMessage message=new SimpleMailMessage();
           message.setTo(email);
           message.setSubject("your opt");
           message.setText("your opt it "+opt+"expiry in 5 minutes");
           javaMailSender.send(message);
         
    }

}
