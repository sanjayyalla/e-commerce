package com.jocata.data.feedback;

import com.jocata.datamodel.reviewandratings.entity.Feedback;

import java.util.List;

public interface FeedbackDao {

    Feedback save(Feedback feedback);

    List<Feedback> getFeedbacksByProductId(Integer productId);
}
