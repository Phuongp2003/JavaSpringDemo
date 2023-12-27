package com.example.springdemo.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginForm {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model) {
        // The username will be available in the model
        String username = (String) model.getAttribute("username");
        System.out.println("Username: " + username);

        // If the username is null or empty, set it to "None"
        if (username == null || username.isEmpty()) {
            username = "None";
        }

        // Add the username to the model
        model.addAttribute("username", username);

        return "home";
    }

}
