package com.fmc.fixmycity.post.comment;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class CommentService {
    public String createComment(Comment comment) throws ExecutionException, InterruptedException {
        comment.setCommentID(generateCommentID(comment.getPostID()));

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

        comment.setDay(day);
        comment.setMonth(month);
        comment.setYear(year);
        comment.setHour(hour);
        comment.setMinute(minute);
        comment.setSecond(second);
        comment.setTime(time);

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts/"+comment.getPostID()+"/comments").document(comment.getCommentID()).set(comment);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    private String generateCommentID(String postID) throws ExecutionException, InterruptedException {
        String id = String.valueOf(getCommentList(postID).size()+1);
        while (getComment(postID, id) != null){
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        return id;
    }

    public Comment getComment(String postID, String commentID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("posts/"+postID+"/comments").document(commentID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Comment comment;
        if(document.exists()){
            comment = document.toObject(Comment.class);
            return comment;
        }
        return null;
    }

    public List<Comment> getCommentList(String postID) throws ExecutionException, InterruptedException {
        List<Comment> commentList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts/"+postID+"/comments").get().get().forEach(d -> commentList.add(d.toObject(Comment.class)));
        return Collections.unmodifiableList(commentList);
    }

    public ResponseEntity<HttpStatus> deleteComment(String postID, String commentID) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        try {
            ApiFuture<WriteResult> writeResult = dbFirestore.collection("posts/"+postID+"/comments").document(commentID).delete();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public List<String> getLikedBy(String postID, String commentID) throws ExecutionException, InterruptedException {
        List<String> likedByList;
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Object docObject = dbFirestore.collection("posts/"+postID+"/comments").select("likedBy").whereEqualTo("commentID", commentID).get().get().getDocuments().get(0).get("likedBy");
        likedByList = (List<String>) docObject;
//        System.out.println(likedByList);
        return likedByList;
    }

    public String likeComment(String postID, String commentID, String email) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Map<String, Object> addUserToArrayMap = new HashMap<>();
        addUserToArrayMap.put("likedBy", FieldValue.arrayUnion(email));
        dbFirestore.collection("posts/"+postID+"/comments").document(commentID).update(addUserToArrayMap);

        return "PostID: " + postID + " CommentID: " + commentID + " liked by: " +email;
    }

    public String unLikeComment(String postID, String commentID, String email) throws ExecutionException, InterruptedException {
        List<String> likedBy = getLikedBy(postID, commentID);

        if(likedBy != null) {
            if (likedBy.contains(email)) {
                Firestore dbFirestore = FirestoreClient.getFirestore();
                likedBy.remove(email);
                dbFirestore.collection("posts/" + postID + "/comments").document(commentID).update("likedBy", likedBy);
                return "PostID: " + postID + " CommentID: " + commentID + " unliked by: " + email;
            } else {
                return "already unliked";
            }
        }
        else {
            return "doesn't exists";
        }
    }
}
