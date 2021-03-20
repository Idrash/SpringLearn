package org.idrash;

import org.idrash.persistence.models.Authority;
import org.idrash.persistence.models.User;
import org.idrash.persistence.repository.AuthorityRepository;
import org.idrash.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class App {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }

    @Bean
    public CommandLineRunner initDb(){
        return args -> {
            //initialisation temporaire de l'utilisateur
            User user = new User();
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin"));
            Authority authority = new Authority();
            authority.setAuthority("ADMIN");
            authority.setId(1);
            authorityRepository.saveAndFlush(authority);
            authority = authorityRepository.getOne(1);
            user.getAuthorities().add(authority);
            userRepository.save(user);
        };
    }
}
