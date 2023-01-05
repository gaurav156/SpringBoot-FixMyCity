package com.fmc.fixmycity.post.postdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/post/details")
public class PostDetailsController {

    @Autowired
    private PostDetailsService postDetailsService;

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PostDetails getPostDetails(@RequestParam("postID") String postID){
        return postDetailsService.getPostDetails(postID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PostDetails> getList(){
        return postDetailsService.getList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PostDetails> filterPostDetailsByEmail(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return postDetailsService.filterPostDetailsByEmail(email);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/assign/worker", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String assignWorker(@RequestParam("postID") String postID, @RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return postDetailsService.assignWorker(postID, email);
    }
}
