package in.authentication.Otpauthentication.repositaries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.authentication.Otpauthentication.entities.user;

@Repository
public interface userrepositaries extends JpaRepository<user,Long>
{
     Optional<user> findByEmail(String email);
}
