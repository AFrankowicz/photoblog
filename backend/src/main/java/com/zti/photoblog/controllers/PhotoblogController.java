package com.zti.photoblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Endpoints for forwarding
 */
@Controller
public class PhotoblogController {

    /**
     * Endpoint forwarding requests to a client
     * @return forward
     */
    @RequestMapping({ "/", "/user/**", "/posts/**", "/login", "/register", "/publish"})
    public String index() {
        return "forward:/index.html";
    }

}