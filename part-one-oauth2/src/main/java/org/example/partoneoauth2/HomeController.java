package org.example.partoneoauth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("")
    public String anonymousHello(){
        return "Anonymous Hello";
    }

    @GetMapping("private")
    public String privateHello(@AuthenticationPrincipal OAuth2User principal){
        return "Good day: " + principal.getAttribute("name");
    }
}
