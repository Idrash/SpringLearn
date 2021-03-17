package org.idrash.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(value = "/resources")
    @ResponseBody
    public String getRessource(){
        return "hello world";
    }



}
