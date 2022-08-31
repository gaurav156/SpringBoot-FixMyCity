package com.fmc.fixmycity.user;

import com.fmc.fixmycity.post.Post;
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
public class UserService {
    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getEmail()).set(user);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUser(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User user;
        if(document.exists()){
            user = document.toObject(User.class);
            return user;
        }
        return null;
    }

    public List<User> getUserList() throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public ResponseEntity<HttpStatus> deleteUser(String email) {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        try {
            ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(email).delete();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }

    public List<User> sortUserByUserType() throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").orderBy("userType").get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }
    public List<User> sortUserByUserName() throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").orderBy("firstName").get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }
    public List<User> sortUserByPostcode() throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").orderBy("postcode").get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }

    public List<User> filterUserByUserType(String value) throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").whereEqualTo("userType", value).get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }
    public List<User> filterUserByPostcode(String value) throws ExecutionException, InterruptedException {
        List<User> userList = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").whereEqualTo("postcode", value).get().get().forEach(d -> userList.add(d.toObject(User.class)));
        return Collections.unmodifiableList(userList);
    }
}
