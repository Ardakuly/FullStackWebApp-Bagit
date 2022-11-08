package bagit.com.repositories;

import bagit.com.entities.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);


}

