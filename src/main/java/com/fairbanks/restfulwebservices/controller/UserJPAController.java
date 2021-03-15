package com.fairbanks.restfulwebservices.controller;

import com.fairbanks.restfulwebservices.exception.PostNotFoundException;
import com.fairbanks.restfulwebservices.exception.UserAlreadyExistsException;
import com.fairbanks.restfulwebservices.exception.UserNotFoundException;
import com.fairbanks.restfulwebservices.model.Post;
import com.fairbanks.restfulwebservices.model.User;
import com.fairbanks.restfulwebservices.repository.UserRepository;
import com.fairbanks.restfulwebservices.service.UserDaoService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserJPAController {

    private final UserDaoService userDaoService;
    private final UserRepository userRepository;


    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        List<User> users = userRepository.findAll();
        if (users == null || users.size() == 0) {
            throw new UserNotFoundException("No users exist");
        }
        return users;
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User Id: " + id);
        }

        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        Integer id = user.getId();
        if (id != null && userRepository.findById(id).isPresent()) {
            throw new UserAlreadyExistsException("User Id: " + id + " already exists");
        }

        User createdUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/jpa/users/{userId}/posts")
    public List<Post> retrieveAllUsers(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User Id: " + userId);
        }
        return user.get().getPosts();
    }

    @GetMapping(path = "/jpa/users/{userId}/posts/{postId}")
    public Post retrieveAllUsers(@PathVariable Integer userId, @PathVariable Integer postId) {
        Post post = userDaoService.findOnePost(userId, postId);
        if (post == null) {
            throw new PostNotFoundException("Post id: " + postId + " doesn't exist for user: " + userId);
        }
        return post;
    }

    @PostMapping(path = "/jpa/users/{userId}/posts")
    public ResponseEntity<Object> addPostToUser(@PathVariable Integer userId, @RequestBody Post post) {
        User user = userDaoService.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException("User Id: " + userId);
        }

        Post createdPost = userDaoService.addPost(userId, post);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
