package com.jocata.controller;

import com.jocata.datamodel.reviewandratings.form.AverageRatingForm;
import com.jocata.datamodel.reviewandratings.form.RatingForm;
import com.jocata.datamodel.reviewandratings.form.ReviewForm;
import com.jocata.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @PostMapping("/reviews/add")
    public ReviewForm addReview(@RequestBody ReviewForm form) {
        return reviewRatingService.addReview(form);
    }

    @GetMapping("/reviews/product/{productId}")
    public List<ReviewForm> getReviewsByProduct(@PathVariable String productId) {
        return reviewRatingService.getReviewsByProduct(productId);
    }

    @PostMapping("/ratings/add")
    public RatingForm addRating(@RequestBody RatingForm form) {
        return reviewRatingService.addRating(form);
    }

    @GetMapping("/ratings/product/{productId}")
    public AverageRatingForm getAverageRating(@PathVariable String productId) {
        return reviewRatingService.getAverageRatingByProduct(productId);
    }
}