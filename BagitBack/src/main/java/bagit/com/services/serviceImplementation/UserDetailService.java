package bagit.com.services.serviceImplementation;

import bagit.com.entities.Admin;
import bagit.com.entities.AppUserDetail;
import bagit.com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> admin = userRepository.findByEmail(username);

        admin.orElseThrow(() -> new UsernameNotFoundException("Only admin can have access to internal tools of \"Bagit\" application!"));

        return new AppUserDetail(admin.get());

    }

    public boolean registerAdmin(Admin admin) {

        Optional<Admin> exist = userRepository.findByEmail(admin.getEmail());

        if (exist.isPresent()) {
            throw new RuntimeException("Admin exists");
        }

        admin.setRole("ADMIN");

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        userRepository.save(admin);

        return true;

    }

}
