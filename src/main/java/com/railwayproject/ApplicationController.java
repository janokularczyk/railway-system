package com.railwayproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/information")
    public String getAboutUsPage() {
        return "about_us";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact_us";
    }

    @GetMapping("/payment")
    public String getPayment() {
        return "payment";
    }
}
