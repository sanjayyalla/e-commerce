package com.jocata.service.impl;

import com.jocata.data.rating.RatingDao;
import com.jocata.data.review.ReviewDao;
import com.jocata.datamodel.reviewandratings.entity.Rating;
import com.jocata.datamodel.reviewandratings.entity.Review;
import com.jocata.datamodel.reviewandratings.form.AverageRatingForm;
import com.jocata.datamodel.reviewandratings.form.RatingForm;
import com.jocata.datamodel.reviewandratings.form.ReviewForm;
import com.jocata.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewRatingServiceImpl implements ReviewRatingService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private RatingDao ratingDao;

    @Override
    public ReviewForm addReview(ReviewForm form) {
        Review review = new Review();
        review.setUserId(Integer.parseInt(form.getUserId()));
        review.setProductId(Integer.parseInt(form.getProductId()));
        review.setReviewText(form.getReviewText());
        Review updated = reviewDao.save(review);

        form.setId(String.valueOf(updated.getId()));
        form.setCreatedAt(updated.getCreatedAt().toString());
        return form;
    }

    @Override
    public List<ReviewForm> getReviewsByProduct(String productId) {
        List<Review> list = reviewDao.findByProductId(Integer.parseInt(productId));
        List<ReviewForm> forms = new ArrayList<>();
        for (Review r : list) {
            ReviewForm form = new ReviewForm();
            form.setId(String.valueOf(r.getId()));
            form.setUserId(String.valueOf(r.getUserId()));
            form.setProductId(String.valueOf(r.getProductId()));
            form.setReviewText(r.getReviewText());
            form.setCreatedAt(r.getCreatedAt().toString());
            forms.add(form);
        }
        return forms;
    }

    @Override
    public RatingForm addRating(RatingForm form) {
        Rating rating = new Rating();
        rating.setUserId(Integer.parseInt(form.getUserId()));
        rating.setProductId(Integer.parseInt(form.getProductId()));
        rating.setRating(Integer.parseInt(form.getRating()));
        Rating updated = ratingDao.save(rating);

        form.setId(String.valueOf(updated.getId()));
        form.setCreatedAt(updated.getCreatedAt().toString());
        return form;
    }

    @Override
    public AverageRatingForm getAverageRatingByProduct(String productId) {
        Double average = ratingDao.findAverageRatingByProductId(Integer.parseInt(productId));
        AverageRatingForm form = new AverageRatingForm();
        form.setProductId(productId);
        form.setAverageRating(String.format("%.2f", average != null ? average : 0.0));
        return form;
    }
}