package com.fmc.fixmycity.post;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {
    public String createPost(Post post) throws ExecutionException, InterruptedException {
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
        int i = 1;
        while(dbFirestore.collection("posts").document(String.valueOf(i)).get().get().exists()) {
            String postID = String.valueOf(i);

            DocumentReference documentReference = dbFirestore.collection("posts").document(postID);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            postList.add(document.toObject(Post.class));
            i++;
        }
        return Collections.unmodifiableList(postList);
    }

    public String updatePost(Post post) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("posts").document(post.getPostID()).set(post);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletePost(String postID) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("posts").document(postID).delete();
        return "Successfully deleted Post ID : "+ postID;
    }
}
