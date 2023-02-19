package com.fmc.fixmycity.post;

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
public class PostService {
    public String createPost(Post post) throws ExecutionException, InterruptedException {
        post.setPostID(generatePostID());

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

        post.setDay(day);
        post.setMonth(month);
        post.setYear(year);
        post.setHour(hour);
        post.setMinute(minute);
        post.setSecond(second);
        post.setTime(time);
        post.setDate(new Date());

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts").document(post.getPostID()).set(post);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Post getPost(String postID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("posts").document(postID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Post post;
        if(document.exists()){
            post = document.toObject(Post.class);
            return post;
        }
        return null;
    }

    public List<Post> getPostList() throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public String updatePost(Post post) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts").document(post.getPostID()).set(post);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public ResponseEntity<HttpStatus> deletePost(String postID) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        try {
            getPost(postID).getCommentIDs().forEach(i -> dbFirestore.collection("posts/"+ postID +"/comments").document(i).delete());
            dbFirestore.collection("posts").document(postID).delete();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public String generatePostID() throws ExecutionException, InterruptedException {
        int ind = getPostList().size();
        int id = ind;
        if(ind > 0) {
            id = Integer.parseInt(getPostList().get(ind - 1).getPostID()) +1;
        }
        if(id < 10){
            id= 10;
        }
        while (getPost(String.valueOf(id)) != null){
            id++;
        }
        return String.valueOf(id);
    }

    public List<Post> sortPostByArea() throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").orderBy("area").get().get().forEach(d -> postList.add(d.toObject(Post.class)));
//        firestore.collection("USERS").get()
//                .addOnSuccessListener { documents ->
//            for (document in documents) {
//                if(document.get("city").equals("karachi") || document.get("city").equals("Karachi")){
//                    arrayList.add(document.toObject(chatModel::class.java));
//                }
//            }
        return Collections.unmodifiableList(postList);
    }

    public List<Post> sortPostByPostcode() throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").orderBy("postcode").get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> sortPostByType() throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").orderBy("type").get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByArea(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("area", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByCity(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("city", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByPostcode(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("postcode", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByEmail(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("email", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByType(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("type", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }

    public List<Post> filterPostByStatus(String value) throws ExecutionException, InterruptedException {
        List<Post> postList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("status", value).get().get().forEach(d -> postList.add(d.toObject(Post.class)));
        return Collections.unmodifiableList(postList);
    }
    public long getPostCountByStatus(String value) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        return dbFirestore.collection("posts").whereEqualTo("status", value).count().get().get().getCount();
    }
    public String updateStatus(String postID, String status) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts").document(postID).update("status", status.toLowerCase());
        if(status.equals("fixed")){
        Date date = new Date();
        dbFirestore.collection("posts").document(postID).update("resolvedDate", String.valueOf(date.getTime()));
        }
        else{
            dbFirestore.collection("posts").document(postID).update("resolvedDate", "");
        }
        return "Status Updated for PostID: " + postID + " at: "+ collectionsApiFuture.get().getUpdateTime();
    }
    public List<String> getLikedBy(String postID) throws ExecutionException, InterruptedException {
        List<String> likedByList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Object docObject = dbFirestore.collection("posts").select("likedBy").whereEqualTo("postID", postID).get().get().getDocuments().get(0).get("likedBy");
        likedByList = (List<String>) docObject;
//        System.out.println(likedByList);
        return likedByList;
    }
    public String likePost(String postID, String email) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        final Map<String, Object> addUserToArrayMap = new HashMap<>();
        addUserToArrayMap.put("likedBy", FieldValue.arrayUnion(email));
        dbFirestore.collection("posts").document(postID).update(addUserToArrayMap);

        return "PostID: " + postID +" liked by: " +email;
    }

    public String unLikePost(String postID, String email) throws ExecutionException, InterruptedException {
        List<String> likedBy = getLikedBy(postID);
        if(likedBy.contains(email)){
            Firestore dbFirestore = FirestoreClient.getFirestore();
            likedBy.remove(email);
            dbFirestore.collection("posts").document(postID).update("likedBy", likedBy);
        return "PostID: " + postID +" unliked by: " +email;
        }
        else {
            return "already unliked";
        }
    }

    public void addCommentID(String postID, String commentID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<String> commentIDs;

        if(getPost(postID).getCommentIDs() != null){
            commentIDs = getPost(postID).getCommentIDs();
            commentIDs.add(commentID);
        }
        else{
        commentIDs = Arrays.asList(commentID);
        }
        dbFirestore.collection("posts").document(postID).update("commentIDs", commentIDs);
    }

    public void removeCommentID(String postID, String commentID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<String> commentIDs = getPost(postID).getCommentIDs();
        commentIDs.remove(commentID);
        dbFirestore.collection("posts").document(postID).update("commentIDs", commentIDs);
    }

    public List<String> getPostIdByPostcode(String value) throws ExecutionException, InterruptedException {
        List<String> postIdList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("posts").whereEqualTo("postcode", value).get().get().forEach(d -> postIdList.add(d.getId()));
        return Collections.unmodifiableList(postIdList);
    }
}
