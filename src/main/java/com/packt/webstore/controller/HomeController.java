package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kamil
 */
@Controller
public class HomeController {

    @RequestMapping({"", "/", "home"})
    public String welcome(Model model){
        model.addAttribute("greeting", "Witaj w sklepie internetowym");
        model.addAttribute("tagline", "Jedynym sklepie internetowym");

        return "welcome";

    }
}
