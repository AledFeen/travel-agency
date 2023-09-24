package bondarenko.travelagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {
    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/index")
    public String home(Principal principal){
        return "index";
    }
}
