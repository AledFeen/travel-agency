package bondarenko.travelagency.controllers;

import bondarenko.travelagency.models.Amenity;
import bondarenko.travelagency.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @PostMapping("/route/review/add")
    public String addReview(@RequestParam("idRoute") int idRoute, @RequestParam("login") String login, @RequestParam("rank") int rank, @RequestParam("description") String description) {
        reviewRepository.addReview(idRoute, login, rank, description);
        return "redirect:/route/" + idRoute;
    }

    @PostMapping("/admin/route/review/delete")
    public String deleteReview(@RequestParam("idRoute") int idRoute, @RequestParam("idReview") int idReview) {
        reviewRepository.deleteReview(idReview);
        return "redirect:/admin/route/" + idRoute;
    }

    @PostMapping("route/review/delete")
    public String deleteReviewByUser(@RequestParam("idRoute") int idRoute, @RequestParam("idReview") int idReview) {
        reviewRepository.deleteReview(idReview);
        return "redirect:/route/" + idRoute;
    }

}
