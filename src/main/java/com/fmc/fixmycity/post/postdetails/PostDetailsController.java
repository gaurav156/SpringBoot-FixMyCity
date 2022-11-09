package com.fmc.fixmycity.post.postdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post/details")
public class PostDetailsController {

    @Autowired
    private PostDetailsService postDetailsService;

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PostDetails> getList(){
        return postDetailsService.getList();
    }
}
