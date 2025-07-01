package com.jocata.service;

import com.jocata.datamodel.reviewandratings.form.FeedbackForm;

import java.util.List;

public interface FeedbackService {

    FeedbackForm addFeedback(FeedbackForm form);

    List<FeedbackForm> getFeedbacksByProductId(String productId);

}
