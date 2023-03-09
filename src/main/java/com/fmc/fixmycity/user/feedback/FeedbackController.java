package com.fmc.fixmycity.user.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createFeedback(@RequestBody Feedback feedback) throws ExecutionException, InterruptedException {
        return feedbackService.createFeedback(feedback);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Feedback getFeedback(@RequestParam String feedbackID) throws ExecutionException, InterruptedException {
        return feedbackService.getFeedback(feedbackID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Feedback> getFeedbacks() throws ExecutionException, InterruptedException {
        return feedbackService.getFeedbacks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Feedback> getFeedbacksByEmail(@RequestParam String email) throws ExecutionException, InterruptedException {
        return feedbackService.getFeedbacksByEmail(email);
    }
}
