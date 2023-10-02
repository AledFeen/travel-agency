package bondarenko.travelagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() { return "main-page"; }

    @GetMapping("/{country}{city}")
    public String filtered_index(@PathVariable(name = "country", required = false) List<String> countryValues,
                           @PathVariable(name = "city", required = false) List<String> cityValues,
                           Model model) {
        if (countryValues != null) {
            for (String value : countryValues)
            {
                System.out.println(value);
            }
        }
        if (cityValues != null) {
            for (String value : cityValues)
            {
                System.out.println(value);
            }
        }
        return "main-page";
    }
    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/index") //future profile
    public String home(Model model, Principal principal){
        return "index";
    }
}


