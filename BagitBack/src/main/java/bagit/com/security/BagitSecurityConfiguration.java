package bagit.com.security;

import bagit.com.entities.AppUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan
public class BagitSecurityConfiguration extends WebSecurityConfigurerAdapter {

        PasswordEncoder passwordEncoder;
        UserDetailsService userDetailService;

        @Autowired
        public BagitSecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService userDetailService) {
            this.passwordEncoder = passwordEncoder;
            this.userDetailService = userDetailService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/**/places/v1/findAll", "/**/places/v1/comment/**", "/**/places/v1/find/**", "/**/signup")
                    .permitAll()
                    .antMatchers("/places/v1/**")
                    .hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
        }




    @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider daoAuthenticationProviderVar = new DaoAuthenticationProvider();
            daoAuthenticationProviderVar.setPasswordEncoder(passwordEncoder);
            daoAuthenticationProviderVar.setUserDetailsService(userDetailService);
            return daoAuthenticationProviderVar;
        }


}
