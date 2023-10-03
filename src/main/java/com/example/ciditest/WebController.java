package com.example.ciditest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index(Model model, String username) {
        model.addAttribute("messages", Messages.getAllMessages());
        model.addAttribute("username", username);
        return "index.html";
    }

    @RequestMapping("/addmessage")
    public String addMessage(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String message, Model model) {
        Messages.addMessage(new Message(username, message));
        return index(model, username);
    }
}
