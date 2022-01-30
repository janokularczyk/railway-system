package com.railwayproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        System.out.println("register request " + user);
        User registeredUser = userService.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        System.out.println("login request " + user);
        User authenticated = userService.authenticate(user.getEmail(), user.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getEmail());
            model.addAttribute("userFirstName", authenticated.getFirstName());
            model.addAttribute("userLastName", authenticated.getLastName());
            return "profile_page";
        } else {
            return "error_page";
        }
    }

}
