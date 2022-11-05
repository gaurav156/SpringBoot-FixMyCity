package com.fmc.fixmycity.post.commentreply;

import com.fmc.fixmycity.post.comment.Comment;
import com.fmc.fixmycity.post.comment.CommentService;
import com.fmc.fixmycity.post.comment.reply.ReplyService;
import com.fmc.fixmycity.user.User;
import com.fmc.fixmycity.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentReplyService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    public CommentReply getCommentReply(String postID, String commentID) {
        CommentReply obj = new CommentReply();

        try {
            Comment c = commentService.getComment(postID, commentID);
            User user = userService.getUser(c.getEmail());
            obj.setCommentID(c.getCommentID());
            obj.setPostID(c.getPostID());
            obj.setEmail(c.getEmail());
            obj.setComment(c.getComment());
            obj.setLikedBy(c.getLikedBy());
            obj.setImageURL(c.getImageURL());
            obj.setDay(c.getDay());
            obj.setMonth(c.getMonth());
            obj.setYear(c.getYear());
            obj.setHour(c.getHour());
            obj.setMinute(c.getMinute());
            obj.setSecond(c.getSecond());
            obj.setTime(c.getTime());
            obj.setUserName(user.getFirstName());
            obj.setProfileImage(user.getProfileImage());
            obj.setReplies(replyService.getReplyList(postID, commentID));
            return obj;
        }
        catch (Exception e){
            return null;
        }
    }
    public List<CommentReply> getList(String postID) {
        List<CommentReply> objList = new ArrayList<>();

        try {
        commentService.getCommentList(postID).forEach(i -> objList.add(getCommentReply(postID, i.getCommentID())));
            return objList;
        }
        catch (Exception e){
            return null;
        }
    }

//    public List<CommentReply> getCommentReplyList(String postID) {
//        List<CommentReply> objList = new ArrayList<>();
//        try {
////            obj.setComment(commentService.getCommentList(postID);
////            obj.setReplies(replyService.getReplyList(obj));
////            return obj;
//            for (Comment item : commentService.getCommentList(postID)) {
//                CommentReply obj = new CommentReply();
//                obj.setComment(commentService.getComment(postID, item.getCommentID()));
//                List<Reply> replies = new ArrayList<>();
//                for (Reply i : replyService.getReplyList(postID, item.getCommentID())) {
//                    replies.add(replyService.getReply(postID, item.getCommentID(), i.getReplyID()));
//                }
//                obj.setReplies(replies);
//                objList.add(obj);
//            }
//            return objList;
//        }
//        catch (Exception e){
//            return null;
//        }
//    }
}
