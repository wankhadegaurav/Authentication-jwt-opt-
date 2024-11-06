package in.authentication.Otpauthentication.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.authentication.Otpauthentication.entities.user;
import in.authentication.Otpauthentication.repositaries.userrepositaries;
import in.authentication.Otpauthentication.services.Otpservice;

@RestController
@RequestMapping("/user")
public class optcontroller 
{  
      @Autowired
    private Otpservice otpservice;

   
  
    @Autowired
   private userrepositaries userrepositaries;


   @PostMapping("/generate")
    public String generateopt(@RequestParam String email)
    {
           return otpservice.generateotp(email);
    }

   @PostMapping("/validate")
    public ResponseEntity<Boolean> validateopt(@RequestParam String email,@RequestParam String otp){
            
                     
            boolean status= otpservice.validateopt(email, otp);
            return ResponseEntity.ok(status);
            
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody user user){
           user.setOpt(null);
           user.setOtpexpiry(null);

           userrepositaries.save(user);
        return ResponseEntity.ok("user is registed ");
    }
    
}
