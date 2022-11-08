package bagit.com.services.serviceImplementation;

import bagit.com.entities.Admin;
import bagit.com.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.Mockito.*;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class UserDetailServiceTest {

    private UserDetailService userDetailService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Admin admin;

    @Mock private UserRepository userRepository;


    public  UserDetailServiceTest() {

        MockitoAnnotations.openMocks(this);
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userDetailService = new UserDetailService(userRepository, bCryptPasswordEncoder);

    }

    @BeforeEach
    void setUp() {

        Admin admin = new Admin();
        admin.setFirstName("Alisher");
        admin.setSecondName("Anuarbekov");
        admin.setEmail("alisher007@example.com");
        admin.setRole("ADMIN");
        admin.setPassword(bCryptPasswordEncoder.encode("PasswordForTest"));
        admin.setEnabled(true);
        this.admin = admin;
    }

    @AfterEach
    void tearDown() {


    }

    @Test
    void loadUserByUsername() {

        //given

        //when

        //when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.ofNullable(admin));
        UserDetails userDetail = userDetailService.loadUserByUsername(admin.getEmail());

        //then

        // assertThatThrownBy(()->userDetailsService.loadUserByUsername(admin.getEmail()))
        //        .isInstanceOf(UsernameNotFoundException.class)
        //        .hasMessageContaining(String.format("Only admin can have access to internal tools of \"Bagit\" application!"));


        assertThat(userDetail.getPassword()).isEqualTo(admin.getPassword());


    }

    @Test
    void registerAdmin() {

        //given

        //when

        //when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.ofNullable(admin));
        when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.ofNullable(null));
        boolean result =  userDetailService.registerAdmin(admin);

        //then

       //assertThatThrownBy(()->userDetailService.registerAdmin(admin))
        //        .isInstanceOf(RuntimeException.class)
        //       .hasMessageContaining("Admin exists");

       verify(userRepository, times(1)).save(admin);
       assertThat(result).isTrue();
    }
}