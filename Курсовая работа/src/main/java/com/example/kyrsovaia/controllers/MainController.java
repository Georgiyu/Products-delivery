package com.example.kyrsovaia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class MainController {

    @GetMapping("/")
    public String MainPage( Model model) {
        model.addAttribute("title", "Доставка продуктов");
        return "index";
    }
    @GetMapping("/about_us")
    public String AboutUsPage( Model model) {
        model.addAttribute("title", "О нас");
        return "about_us";
    }
    @GetMapping("/vids_usl")
    public String Vids_uslPage( Model model) {
        model.addAttribute("title", "О нас");
        return "vids_usl";
    }
    @GetMapping("/auth")
    public String Auth( Model model) {
        model.addAttribute("title", "Форма входа для владелеца");
        return "auth";
    }

}