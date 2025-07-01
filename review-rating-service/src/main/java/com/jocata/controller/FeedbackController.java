package com.jocata.controller;

import com.jocata.datamodel.reviewandratings.form.FeedbackForm;
import com.jocata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/add")
    public FeedbackForm addFeedback(@RequestBody FeedbackForm form) {
        return feedbackService.addFeedback(form);
    }

    @GetMapping("/byProduct/{productId}")
    public List<FeedbackForm> getFeedbacksByProduct(@PathVariable String productId) {
        return feedbackService.getFeedbacksByProductId(productId);
    }
}

