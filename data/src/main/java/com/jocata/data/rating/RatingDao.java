package com.jocata.data.rating;

import com.jocata.datamodel.reviewandratings.entity.Rating;

public interface RatingDao {

    Rating save(Rating rating);

    Double findAverageRatingByProductId(Integer productId);
}
