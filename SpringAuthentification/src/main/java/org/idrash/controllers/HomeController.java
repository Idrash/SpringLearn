package org.idrash.controllers;


import org.idrash.persistence.models.Authority;
import org.idrash.persistence.models.User;
import org.idrash.persistence.repository.AuthorityRepository;
import org.idrash.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @RequestMapping(value = "/")
    @ResponseBody
    public String getRessource() {
        return "hello world";
    }

    @RequestMapping(value = "/role")
    @ResponseBody
    public String setRole() {
        Authority authority = authorityRepository.getOne(1);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(authentication.getName());
        if (user == null) {
            return "user not found";
        }
        user.getAuthorities().add(authority);
        userRepository.save(user);
        return "role " + authority.getAuthority() + " to the user : " + user.getUsername();
    }



}
