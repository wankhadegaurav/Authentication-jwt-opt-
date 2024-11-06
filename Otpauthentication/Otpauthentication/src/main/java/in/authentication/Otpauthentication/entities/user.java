package in.authentication.Otpauthentication.entities;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class user 
{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String email;
      private String password;
      private String opt;

      private LocalDateTime otpexpiry;




}
