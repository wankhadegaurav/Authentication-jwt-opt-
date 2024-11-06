package in.authentication.Otpauthentication.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.authentication.Otpauthentication.entities.user;
import in.authentication.Otpauthentication.repositaries.userrepositaries;

@Service
public class passwordreset 
{
    @Autowired
    private userrepositaries userrepositaries;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void resetpassword(String email,String opt,String newpassword){
        Optional<user>optionaluser=userrepositaries.findByEmail(email);
        if(optionaluser!=null){
            new IllegalAccessException("invalid email address"+email);
        }
        user user =optionaluser.get();
         
        if(!opt.equals(user.getOpt())&&LocalDateTime.now().isAfter(user.getOtpexpiry())){
            new IllegalAccessError("opt is invalid try again");
        }

        user.setPassword(passwordEncoder.encode(newpassword));
        user.setOpt(null);
        user.setOtpexpiry(null);
        userrepositaries.save(user);


    }



    
}
