package com.jocata.service.impl;

import com.jocata.data.feedback.FeedbackDao;
import com.jocata.datamodel.reviewandratings.entity.Feedback;
import com.jocata.datamodel.reviewandratings.form.FeedbackForm;
import com.jocata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public FeedbackForm addFeedback(FeedbackForm form) {
        Feedback feedback = new Feedback();
        feedback.setUserId(Integer.parseInt(form.getUserId()));
        feedback.setProductId(Integer.parseInt(form.getProductId()));
        feedback.setFeedbackText(form.getFeedbackText());

        Feedback savedFeedback = feedbackDao.save(feedback);

        FeedbackForm responseForm = convertToForm(savedFeedback);
        responseForm.setMessage("Feedback submitted successfully");

        return responseForm;
    }

    @Override
    public List<FeedbackForm> getFeedbacksByProductId(String productId) {
        List<Feedback> feedbackList = feedbackDao.getFeedbacksByProductId(Integer.parseInt(productId));
        List<FeedbackForm> formList = new ArrayList<>();

        for (Feedback feedback : feedbackList) {
            FeedbackForm form = convertToForm(feedback);
            formList.add(form);
        }

        return formList;
    }

    private FeedbackForm convertToForm(Feedback feedback) {
        FeedbackForm form = new FeedbackForm();
        form.setId(String.valueOf(feedback.getId()));
        form.setUserId(String.valueOf(feedback.getUserId()));
        form.setProductId(String.valueOf(feedback.getProductId()));
        form.setFeedbackText(feedback.getFeedbackText());
        return form;
    }
}
