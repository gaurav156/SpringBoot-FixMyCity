package com.fmc.fixmycity.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpStatus> deletePost(@RequestParam String postID){
        return postService.deletePost(postID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> testing(){
        return ResponseEntity.ok("Working");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/area", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> sortPostByArea() throws ExecutionException, InterruptedException {
        return postService.sortPostByArea();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> sortPostByPostcode() throws ExecutionException, InterruptedException {
        return postService.sortPostByPostcode();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/type", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> sorPostByType() throws ExecutionException, InterruptedException {
        return postService.sortPostByType();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/area", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByArea(@RequestParam("area") String area) throws ExecutionException, InterruptedException {
        return postService.filterPostByArea(area);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/city", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByCity(@RequestParam("city") String city ) throws ExecutionException, InterruptedException {
        return postService.filterPostByCity(city);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByPostcode(@RequestParam("postcode") String postcode ) throws ExecutionException, InterruptedException {
        return postService.filterPostByPostcode(postcode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByEmail(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return postService.filterPostByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/type", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByType(@RequestParam("type") String type) throws ExecutionException, InterruptedException {
        return postService.filterPostByType(type);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/status", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Post> filterPostByStatus(@RequestParam("status") String status) throws ExecutionException, InterruptedException {
        return postService.filterPostByStatus(status);
    }
}
