package org.idrash.controllers;


import org.idrash.persistence.models.Authority;
import org.idrash.persistence.models.User;
import org.idrash.persistence.repository.AuthorityRepository;
import org.idrash.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@RestController
public class HomeController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @PostConstruct
    public void init(){
        //initialisation temporaire de l'utilisateur
        User user = new User();
        user.setUsername("admin");
        user.setPassword(encoder.encode("admin"));
        Authority authority = new Authority();
        authority.setAuthority("ADMIN");
        authority.setId(1);
        authorityRepository.save(authority);
        authority = authorityRepository.getOne(1);
        Set<Authority> perms = new HashSet<>();
        perms.add(authority);
        System.out.println(authority.getAuthority()+" : "+authority.getId());
//        user.setAuthorities(perms); //not work
        userRepository.save(user);


    }


    @RequestMapping(value = "/")
    @ResponseBody
    public String getRessource(){
        return "hello world";
    }



}
