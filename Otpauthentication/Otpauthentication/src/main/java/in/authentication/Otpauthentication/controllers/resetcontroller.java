package in.authentication.Otpauthentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.authentication.Otpauthentication.services.Otpservice;
import in.authentication.Otpauthentication.services.passwordreset;

@RestController
@RequestMapping("user/reset")
public class resetcontroller
{
    @Autowired
    private passwordreset passwordreset;
     
    @Autowired
     private Otpservice otpservice;

     

    @PostMapping("/generate")
     public String generateresetopt(@RequestParam String email){
         return otpservice.generateotp(email);
     }





@PostMapping("/password")
   public ResponseEntity<String> resetpassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newpassword){

        if(otpservice.validateopt(email, otp)){
            passwordreset.resetpassword(email, otp, newpassword);
            return ResponseEntity.ok("your password is reseted");
        }     
      
      
            return ResponseEntity.ok("your password not is reseted");
           


      
        

   }

    
}
