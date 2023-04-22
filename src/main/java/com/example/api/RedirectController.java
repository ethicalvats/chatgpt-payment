package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/success")
    public String handleSuccessRedirect() {
        // handle success redirect
        return "success";
    }

    @GetMapping("/failure")
    public String handleFailureRedirect() {
        // handle failure redirect
        return "failure";
    }
}
