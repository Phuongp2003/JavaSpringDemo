package com.example.springdemo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping({ "/", "/home" })
	public String home(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		if (username == null || username.isEmpty()) {
			username = "None";
			session.setAttribute("username", username);
		}
		model.addAttribute("username", username);
		return "home";
	}

}
