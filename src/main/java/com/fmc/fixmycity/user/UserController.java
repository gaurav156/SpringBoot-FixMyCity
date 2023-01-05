package com.fmc.fixmycity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserDetails(@RequestParam String email) throws ExecutionException, InterruptedException {
        return userService.getUserDetails(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUserList() throws ExecutionException, InterruptedException {
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUserDetails(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUserDetails(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam String email){
        return userService.deleteUser(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/userType", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> sortUserByUserType() throws ExecutionException, InterruptedException {
        return userService.sortUserByUserType();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/name", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> sortUserByName() throws ExecutionException, InterruptedException {
        return userService.sortUserByUserName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> sortUserByPostcode() throws ExecutionException, InterruptedException {
        return userService.sortUserByPostcode();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/filter/userType", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> filterUserByUserType(@RequestParam("userType") String type) throws ExecutionException, InterruptedException {
        return userService.filterUserByUserType(type);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/users/filter/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> filterUserByPostcode(@RequestParam("postcode") String postcode) throws ExecutionException, InterruptedException {
        return userService.filterUserByPostcode(postcode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User authenticateUser(@RequestParam("email") String email , @RequestParam("password") String password) throws ExecutionException, InterruptedException {
        return userService.authenticateUser(email, password);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reset", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> resetPassword(@RequestParam("email") String email, @RequestParam("password") String password){
        return userService.resetPassword(email, password);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean checkEmail(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return userService.checkEmail(email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/type", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUserType(@RequestParam("email") String email, @RequestParam("userType") String userType) throws ExecutionException, InterruptedException {
        return userService.updateUserType(email, userType);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/assign/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String assignPostcode(@RequestParam("email")String email, @RequestBody List<String> assignedPostcode) throws ExecutionException, InterruptedException {
        return userService.assignPostcode(email, assignedPostcode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/worker/filter/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> filterWorkersByPostcode(@RequestParam("postcode") String postcode) throws ExecutionException, InterruptedException {
        return userService.filterWorkersByPostcode(postcode);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/worker/assign/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String setCurrentAssignedPosts(@RequestParam("email") String email, @RequestParam("postID") String postID) throws ExecutionException, InterruptedException {
        return userService.setCurrentAssignedPosts(email, postID);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/worker/get/assign/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getCurrentAssignedPosts(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return userService.getCurrentAssignedPosts(email);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/worker/resolved/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String setResolvedPosts(@RequestParam("email") String email, @RequestParam("postID") String postID) throws ExecutionException, InterruptedException {
        return userService.setResolvedPosts(email, postID);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/worker/get/resolved/post", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<String> getResolvedPosts(@RequestParam("email") String email) throws ExecutionException, InterruptedException {
        return userService.getResolvedPosts(email);
    }

}
