package com.fairbanks.restfulwebservices.controller;

import com.fairbanks.restfulwebservices.exception.UserNotFoundException;
import com.fairbanks.restfulwebservices.exception.PostNotFoundException;
import com.fairbanks.restfulwebservices.exception.UserAlreadyExistsException;
import com.fairbanks.restfulwebservices.model.Post;
import com.fairbanks.restfulwebservices.model.User;
import com.fairbanks.restfulwebservices.service.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        List<User> users = userDaoService.findAll();
        if(users == null || users.size() == 0){
            throw new UserNotFoundException("No users exist");
        }
        return users;
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        User user = userDaoService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("User Id: "+ id);
        }
        return user;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        User user = userDaoService.deleteById(id);
        if(user == null){
            throw new UserNotFoundException("User Id: "+ id);
        }
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        Integer id = user.getId();
        if(id != null && userDaoService.findOne(id) != null){
            throw new UserAlreadyExistsException("User Id: "+ id + " already exists");
        }

        User createdUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{userId}/posts")
    public List<Post> retrieveAllUsers(@PathVariable Integer userId){
        List<Post> posts = userDaoService.findAllPosts(userId);
        if(posts == null || posts.size() == 0){
            throw new PostNotFoundException("No posts exist for user: "+userId);
        }
        return posts;
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}")
    public Post retrieveAllUsers(@PathVariable Integer userId, @PathVariable Integer postId){
        Post post = userDaoService.findOnePost(userId, postId);
        if(post == null){
            throw new PostNotFoundException("Post id: "+postId+" doesn't exist for user: "+userId);
        }
        return post;
    }

    @PostMapping(path = "/users/{userId}/posts")
    public  ResponseEntity<Object> addPostToUser(@PathVariable Integer userId, @RequestBody Post post){
        User user = userDaoService.findOne(userId);
        if(user == null){
            throw new UserNotFoundException("User Id: "+ userId);
        }

        Post createdPost = userDaoService.addPost(userId, post);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
