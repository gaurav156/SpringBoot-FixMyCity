package com.fmc.fixmycity.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public User authenticateUser(String email , String password) throws ExecutionException, InterruptedException {
        User result = null;
        for (User i : getUserList()) {
            if (i.getEmail().equals(email) & i.getPassword().equals(password)) {
                result = i;
                break;
            }
        }

        return result;
    }

    public ResponseEntity<HttpStatus> resetPassword(String email, String password) {
        User result = new User();
        try {
            for (User i : getUserList()) {
                if (i.getEmail().equals(email)) {
                    result = i;
                    break;
                }
            }
            result.setPassword(password);
            updateUser(result);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean checkEmail(String email) throws ExecutionException, InterruptedException {
        boolean present = false;
        for (User i : getUserList()) {
            if (i.getEmail().equals(email)) {
                present = true;
                break;
            }
        }
        return present;
    }

    public User getUserDetails(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("users").document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User user;
        if(document.exists()){
            user = document.toObject(User.class);
            user.setPassword("");
            return user;
        }
        return null;
    }
    public String updateUserDetails(User user) throws ExecutionException, InterruptedException {
        user.setPassword(getUser(user.getEmail()).getPassword());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateUserType(String email, String userType) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").document(email).update("userType", userType.toLowerCase());

        return email + " is now "+userType;
    }

    public String assignPostcode(String email, List<String> assignedPostcode) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

//       Object list = dbFirestore.collection("users").document(email).get().get().get("assignedPostcode");
//        System.out.println(list);
////        if(list != null){
////            list.addAll(assignedPostcode);
//        List<String> postcodes = new ArrayList<>();
//        if (list != null) {
//            postcodes.addAll((Collection<? extends String>) list);
//        }
//
//        if (postcodes != null) {
//            for(String val: assignedPostcode) {
//                if(!postcodes.contains(val)) {
//                    postcodes.add(val);
//                }
//            }
////            postcodes.addAll(assignedPostcode);
//        }
//        }
        dbFirestore.collection("users").document(email).update("assignedPostcode", assignedPostcode);
        return email + " has be assigned postcode : " + assignedPostcode;
    }

    public String getUserType(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentSnapshot document = dbFirestore.collection("users").document(email).get().get();

        String userType;
        if(document.exists()){
            userType = document.getString("userType");
            return userType;
        }
        return null;
    }

    public List<String> getAssignedPostcode(String email) throws ExecutionException, InterruptedException {
        User user = getUserDetails(email);
        if(user != null){
            return user.getAssignedPostcode();
        }
        return null;
    }
}
