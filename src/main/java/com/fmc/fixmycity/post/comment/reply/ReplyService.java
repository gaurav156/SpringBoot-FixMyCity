package com.fmc.fixmycity.post.comment.reply;

import com.fmc.fixmycity.user.User;
import com.fmc.fixmycity.user.UserService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class ReplyService {
    @Autowired
    private UserService userService;

    public String replyOnComment(String postID, Reply reply) throws ExecutionException, InterruptedException {
        reply.setReplyID(generateReplyID(postID, reply.getCommentID()));

        Date date = new Date();
        Calendar now = Calendar.getInstance();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm a");
        String time = formatDate.format(new Date()).toString();

        reply.setDay(day);
        reply.setMonth(month);
        reply.setYear(year);
        reply.setHour(hour);
        reply.setMinute(minute);
        reply.setSecond(second);
        reply.setTime(time);

        User user = userService.getUser(reply.getEmail());
        reply.setUserName(user.getFirstName());
        reply.setProfileImage(user.getProfileImage());

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts/"+postID+"/comments/"+reply.getCommentID()+"/replies").document(reply.getReplyID()).set(reply);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    private String generateReplyID(String postID, String commentID) throws ExecutionException, InterruptedException {
        String id = String.valueOf(getReplyList(postID, commentID).size()+1);
        while (getReply(postID, commentID, id) != null){
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        return id;
    }

    public Reply getReply(String postID, String commentID, String replyID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").document(replyID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Reply reply;
        if(document.exists()){
            reply = document.toObject(Reply.class);
            return reply;
        }
        return null;
    }

    public List<Reply> getReplyList(String postID, String commentID) throws ExecutionException, InterruptedException {
        List<Reply> replyList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").get().get().forEach(d -> replyList.add(d.toObject(Reply.class)));
        return Collections.unmodifiableList(replyList);
    }

    public ResponseEntity<HttpStatus> deleteReply(String postID, String commentID, String replyID) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        try {
            ApiFuture<WriteResult> writeResult = dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").document(replyID).delete();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public List<String> getLikedBy(String postID, String commentID, String replyID) throws ExecutionException, InterruptedException {
        List<String> likedByList;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Object docObject = dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").select("likedBy").whereEqualTo("replyID", replyID).get().get().getDocuments().get(0).get("likedBy");
        likedByList = (List<String>) docObject;
//        System.out.println(likedByList);
        return likedByList;
    }

    public String likeReply(String postID, String commentID, String replyID, String email) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Map<String, Object> addUserToArrayMap = new HashMap<>();
        addUserToArrayMap.put("likedBy", FieldValue.arrayUnion(email));
        dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").document(replyID).update(addUserToArrayMap);

        return "PostID: " + postID + "CommentID: " + commentID + " ReplyID: " + replyID + " liked by: " +email;
    }

    public String unLikeReply(String postID, String commentID, String replyID, String email) throws ExecutionException, InterruptedException {
        List<String> likedBy = getLikedBy(postID, commentID, replyID);

        if(likedBy != null){
            if(likedBy.contains(email)){
                Firestore dbFirestore = FirestoreClient.getFirestore();
                likedBy.remove(email);
                dbFirestore.collection("posts/"+postID+"/comments/"+commentID+"/replies").document(replyID).update("likedBy", likedBy);
                return "PostID: " + postID + " CommentID: " + commentID + " ReplyID: " + replyID + " unliked by: " +email;
            }
            else {
                return "already unliked";
            }
        }
        else{
            return "doesn't exists";
        }
    }
}
