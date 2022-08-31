package com.fmc.fixmycity.post;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {
    public String createPost(Post post) throws ExecutionException, InterruptedException {
        post.setPostID(generatePostID());

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm a");
        String time = formatDate.format(new Date()).toString();

        post.setDay(day);
        post.setMonth(month);
        post.setYear(year);
        post.setTime(time);

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
            ApiFuture<WriteResult> writeResult = dbFirestore.collection("posts").document(postID).delete();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public String generatePostID() throws ExecutionException, InterruptedException {
        String id = String.valueOf(getPostList().size()+1);
        return id;
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
}
