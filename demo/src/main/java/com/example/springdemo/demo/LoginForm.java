package com.example.springdemo.demo;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;

@Controller
public class LoginForm {
    private static final Logger logger = LoggerFactory.getLogger(LoginForm.class);

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password,
            HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, HttpSession session) {
        // The username will be available in the session
        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
            logger.info("No username present, redirecting to login page.");
            return "redirect:/login";
        }

        logger.info("Username {} login success!", username);
        model.addAttribute("username", username);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            logger.info("No username present, redirecting to login page.");
            return "redirect:/home";
        }

        logger.info("Username {} logout success!", username);
        redirectAttributes.addFlashAttribute("logout", true);
        session.removeAttribute("username");
        return "redirect:/home";
    }

}
