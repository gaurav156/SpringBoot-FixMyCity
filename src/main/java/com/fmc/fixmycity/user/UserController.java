package com.fmc.fixmycity.user;

import com.fmc.fixmycity.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public User getUser(@RequestParam String email) throws ExecutionException, InterruptedException {
        return userService.getUser(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUserList() throws ExecutionException, InterruptedException {
        return userService.getUserList();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam String email){
        return userService.deleteUser(email);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/type", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/type", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> filterUserByUserType(@RequestParam("type") String type) throws ExecutionException, InterruptedException {
        return userService.filterUserByUserType(type);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/posts/filter/postcode", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> filterUserByPostcode(@RequestParam("postcode") String postcode) throws ExecutionException, InterruptedException {
        return userService.filterUserByPostcode(postcode);
    }

}
