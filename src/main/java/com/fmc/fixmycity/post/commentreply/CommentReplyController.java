package com.fmc.fixmycity.post.commentreply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment/reply")
public class CommentReplyController {

    @Autowired
    private CommentReplyService commentReplyService;

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CommentReply> getList(@RequestParam("postID") String postID){
        return commentReplyService.getList(postID);
    }
}
