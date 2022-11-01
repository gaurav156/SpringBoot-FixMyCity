package com.fmc.fixmycity.post.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createComment(@RequestBody Comment comment) throws ExecutionException, InterruptedException {
        return commentService.createComment(comment);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Comment getComment(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID) throws ExecutionException, InterruptedException {
        return commentService.getComment(postID, commentID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Comment> getCommentList(@RequestParam("postID") String postID) throws ExecutionException, InterruptedException {
        return commentService.getCommentList(postID);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteComment(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID){
        return commentService.deleteComment(postID, commentID);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/like", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String likeComment(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("email") String email) {
        return commentService.likeComment(postID, commentID,email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/unlike", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String unLikeComment(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return commentService.unLikeComment(postID, commentID, email);
    }

}
