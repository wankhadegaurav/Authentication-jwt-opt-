package in.authentication.Otpauthentication.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.authentication.Otpauthentication.entities.user;
import in.authentication.Otpauthentication.repositaries.userrepositaries;

@Service
public class Otpservice 
{
    @Autowired
    private userrepositaries userrepositaries;

    @Autowired
    private EmailService EmailService;


    public String generateotp(String  email){
           Optional<user> optionaluser=userrepositaries.findByEmail(email);

           if(optionaluser!=null){
            new IllegalAccessException("user not found"+email);
           }
           user user=optionaluser.get();
           String opt=String.valueOf(new Random().nextInt(999999));
           user.setOpt(opt);
           user.setOtpexpiry(LocalDateTime.now().plusMinutes(5));

           userrepositaries.save(user);
            EmailService.sendemailotp(email, opt);
           return opt;
    }

    public boolean validateopt(String email,String opt){
         Optional<user>optionaluser=userrepositaries.findByEmail(email);

         if(optionaluser.isEmpty()){
            return false;
         }
          user user=optionaluser.get();
          return opt.equals(user.getOpt())&&LocalDateTime.now().isBefore(user.getOtpexpiry());
    }

    
}
