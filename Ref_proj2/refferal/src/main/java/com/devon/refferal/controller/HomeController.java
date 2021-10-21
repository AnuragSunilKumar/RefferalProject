package com.devon.refferal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String home(Model model)
    {
    	model.addAttribute("title","Home-Employee Refferal Program");
        return "home";
    }
    
    @RequestMapping("/about")
    public String about(Model model)
    {
    	model.addAttribute("title","About-Employee Refferal Program");
        return "about";
    }
    
    @RequestMapping("/refferalform")
    public String refferalForm(Model model)
    {
    	model.addAttribute("title"," Refferal form");
        return "refferalform";
    }
}
