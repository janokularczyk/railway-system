package com.railwaysystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/search")
    public String getSearchPage() {
        return "search_page";
    }

}
