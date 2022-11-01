package com.fmc.fixmycity.post.comment.reply;

import com.fmc.fixmycity.post.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String replyOnComment(@RequestParam("postID") String postID, @RequestBody Reply reply) throws ExecutionException, InterruptedException {
        return replyService.replyOnComment(postID, reply);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Reply getReply(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("replyID") String replyID) throws ExecutionException, InterruptedException {
        return replyService.getReply(postID, commentID, replyID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/replies", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Reply> getReplyList(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID) throws ExecutionException, InterruptedException {
        return replyService.getReplyList(postID, commentID);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteReply(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("replyID") String replyID){
        return replyService.deleteReply(postID, commentID, replyID);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/like", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String likeReply(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("replyID") String replyID, @RequestParam("email") String email) {
        return replyService.likeReply(postID, commentID,replyID, email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/unlike", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String unLikeReply(@RequestParam("postID") String postID, @RequestParam("commentID") String commentID, @RequestParam("replyID") String replyID, @RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return replyService.unLikeReply(postID, commentID, replyID, email);
    }
}
