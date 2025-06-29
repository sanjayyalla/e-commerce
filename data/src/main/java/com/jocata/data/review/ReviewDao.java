package com.jocata.data.review;

import com.jocata.datamodel.reviewandratings.entity.Review;

import java.util.List;

public interface ReviewDao {

    Review save(Review review);

    List<Review> findByProductId(Integer productId);
}
