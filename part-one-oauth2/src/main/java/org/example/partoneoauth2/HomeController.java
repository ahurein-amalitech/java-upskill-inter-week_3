package org.example.partoneoauth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String showHome(Model model) {
        return "home";
    }

    @GetMapping("/user")
    public String showUsers(@AuthenticationPrincipal OAuth2User authentication, Model model) {
        User user = new User();
        user.setName(authentication.getName());
        user.setEmail(authentication.getAttribute("email"));
        model.addAttribute("user", user);
        return "user";
    }
}
