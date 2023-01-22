package com.fmc.fixmycity.post.postdetails;

import com.fmc.fixmycity.post.Post;
import com.fmc.fixmycity.post.PostService;
import com.fmc.fixmycity.user.User;
import com.fmc.fixmycity.user.UserService;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class PostDetailsService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public PostDetails getPostDetails(String postID) {
        PostDetails obj = new PostDetails();

        try{
            Post p = postService.getPost(postID);
            User user = userService.getUser(p.getEmail());

            obj.setPostID(p.getPostID());
            obj.setEmail(p.getEmail());
            obj.setLikedBy(p.getLikedBy());
            obj.setImageURL(p.getImageURL());
            obj.setDay(p.getDay());
            obj.setMonth(p.getMonth());
            obj.setYear(p.getYear());
            obj.setHour(p.getHour());
            obj.setMinute(p.getMinute());
            obj.setSecond(p.getSecond());
            obj.setTime(p.getTime());
            obj.setAddress(p.getAddress());
            obj.setStreet_address(p.getStreet_address());
            obj.setArea(p.getArea());
            obj.setCity(p.getCity());
            obj.setPostcode(p.getPostcode());
            obj.setAdditionalDetails(p.getAdditionalDetails());
            obj.setLat(p.getLat());
            obj.setLng(p.getLng());
            obj.setType(p.getType());
            obj.setDescription(p.getDescription());
            obj.setDate(p.getDate());
            obj.setStatus(p.getStatus());
            obj.setResolvedDate(p.getResolvedDate());
            obj.setCommentIDs(p.getCommentIDs());
            obj.setFirstName(user.getFirstName());
            obj.setLastName(user.getLastName());
            obj.setProfileImage(user.getProfileImage());
            obj.setUserType(user.getUserType());
            obj.setAssignedWorker(p.getAssignedWorker());
            obj.setAssignedOn(p.getAssignedOn());

            return obj;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<PostDetails> getList() {
        List<PostDetails> objList = new ArrayList<>();

        try {
            postService.getPostList().forEach(i -> objList.add(getPostDetails(i.getPostID())));
            return objList;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<PostDetails> filterPostDetailsByEmail(String email) throws ExecutionException, InterruptedException {
        List<PostDetails> objList = new ArrayList<>();

        String userType = userService.getUserType(email);

        if(userType.equals("official")){
            List<String> assignedPostcode = userService.getAssignedPostcode(email);
            for (String i : assignedPostcode) {
            postService.getPostIdByPostcode(i).forEach(x -> objList.add(getPostDetails(x)));
            }
            return objList;
        }
        else if(userType.equals("worker")){
            List<String> currentAssignedPosts = userService.getCurrentAssignedPosts(email);
            for (String i : currentAssignedPosts) {
                objList.add(getPostDetails(i));
            }
            List<String> resolvedPosts = userService.getResolvedPosts(email);
            for (String i : resolvedPosts) {
                objList.add(getPostDetails(i));
            }
            return objList;
        }
        else {
            try{
                postService.filterPostByEmail(email).forEach(i -> objList.add(getPostDetails(i.getPostID())));
                return objList;
            }
            catch (Exception e){
                return null;
            }
        }
    }

    public String assignWorker(String postID, String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        try {
            String e = dbFirestore.collection("posts").document(postID).get().get().getString("assignedWorker");
            if (e != null) {
                userService.removeCurrentAssignedPosts(e, postID);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        dbFirestore.collection("posts").document(postID).update("assignedWorker", email);

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dbFirestore.collection("posts").document(postID).update("assignedOn", localDate.toString());

        userService.setCurrentAssignedPosts(email, postID);
        return email+" assigned to resolve postID: "+postID;
    }

}
