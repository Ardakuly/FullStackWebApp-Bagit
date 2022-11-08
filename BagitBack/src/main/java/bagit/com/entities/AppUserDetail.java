package bagit.com.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserDetail implements UserDetails {

    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private  String role;
    private Boolean locked;
    private Boolean enabled;

    public AppUserDetail(Admin admin) {
        this.firstName = admin.getFirstName();
        this.secondName = admin.getSecondName();
        this.password = admin.getPassword();
        this.email = admin.getEmail();
        this.locked = false;
        this.enabled = true;
        this.role = "ADMIN";

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority role = new SimpleGrantedAuthority(this.getRole());

        return Arrays.asList(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getUsername() {
        return firstName + " " + secondName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return email;
    }


}
