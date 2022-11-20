package com.fmc.fixmycity.post.postdetails;

import com.fmc.fixmycity.post.Post;
import com.fmc.fixmycity.post.PostService;
import com.fmc.fixmycity.user.User;
import com.fmc.fixmycity.user.UserService;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<PostDetails> filterPostDetailsByEmail(String email){
        List<PostDetails> objList = new ArrayList<>();

        try{
            postService.filterPostByEmail(email).forEach(i -> objList.add(getPostDetails(i.getPostID())));
            return objList;
        }
        catch (Exception e){
            return null;
        }
    }
}
