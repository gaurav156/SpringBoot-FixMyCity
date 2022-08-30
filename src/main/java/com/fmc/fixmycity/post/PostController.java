package com.fmc.fixmycity.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createPost(@RequestBody Post post) throws ExecutionException, InterruptedException {
        return postService.createPost(post);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Post getPost(@RequestParam String postID) throws ExecutionException, InterruptedException {
        return postService.getPost(postID);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/posts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> getPostList() throws ExecutionException, InterruptedException {
        return postService.getPostList();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updatePost(@RequestBody Post post) throws ExecutionException, InterruptedException {
        return postService.updatePost(post);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deletePost(@RequestParam String postID){
        return postService.deletePost(postID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> testing(){
        return ResponseEntity.ok("Working");
    }
}
