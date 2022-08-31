package com.fmc.fixmycity.email;

import com.fmc.fixmycity.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/otp")
public class EmailSendController {

    @Autowired
    private EmailSendService emailSendService;

    @RequestMapping(method = RequestMethod.GET, value = "/send", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void sendOtp(@RequestParam("email") String email) {
        emailSendService.sendOtp(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/verify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String verifyOtp(@RequestBody Otp givenOtp){
        return emailSendService.verifyOtp(givenOtp);
    }
}
