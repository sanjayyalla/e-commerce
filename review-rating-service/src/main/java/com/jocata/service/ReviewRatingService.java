package com.jocata.service;

import com.jocata.datamodel.reviewandratings.form.AverageRatingForm;
import com.jocata.datamodel.reviewandratings.form.RatingForm;
import com.jocata.datamodel.reviewandratings.form.ReviewForm;

import java.util.List;

public interface ReviewRatingService {
    ReviewForm addReview(ReviewForm form);

    List<ReviewForm> getReviewsByProduct(String productId);

    RatingForm addRating(RatingForm form);

    AverageRatingForm getAverageRatingByProduct(String productId);
}
