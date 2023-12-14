package bondarenko.travelagency.controllers;

import bondarenko.travelagency.repositories.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class DealController {
    @Autowired
    DealRepository dealRepository;

    @GetMapping("/deals")
    public String getDeals(Model model) {
        model.addAttribute("deals", dealRepository.getAllDeals());
        return "deals";
    }

    @GetMapping("/choices/{idDeal}")
    public String getChanges(@PathVariable("idDeal") int idDeal, Model model) {
        model.addAttribute("choices", dealRepository.getAllChoicesByDealId(idDeal));
        return "choices";
    }

    @PostMapping("/deal/delete")
    public String deleteDeal(@ModelAttribute("idDeal") int idDeal) {
        dealRepository.deleteDeal(idDeal);
        return "redirect:/admin/deals";
    }
}
