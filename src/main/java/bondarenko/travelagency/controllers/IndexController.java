package bondarenko.travelagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() { return "main-page"; }
    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/index")
    public String home(Model model, Principal principal){
        return "index";
    }


}


