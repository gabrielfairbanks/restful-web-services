package com.fairbanks.restfulwebservices.service;

import com.fairbanks.restfulwebservices.model.Post;
import com.fairbanks.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> users = new ArrayList<>();
    private static int usersCount = 6;

    static {
        users.add(new User(1, "Gabriel", new Date(), null));
        users.add(new User(2, "Julia", new Date(), null));
        users.add(new User(3, "Bia", new Date(), null));
        users.add(new User(4, "Nando", new Date(), null));
        users.add(new User(5, "Isabella", new Date(), null));
        users.add(new User(6, "Scarlet", new Date(), null));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Integer id) {
        return users.stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }

    public User deleteById(Integer id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId().equals(id)){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<Post> findAllPosts(Integer userId){
        User user = findOne(userId);
        if(user != null) {
            return user.getPosts();
        }
        return null;
    }

    public Post addPost(Integer userId, Post post){
        User user = findOne(userId);
        List<Post> posts = user.getPosts();
        int userPostsCount = posts.size();

        if (post.getId() == null) {
            post.setId(++userPostsCount);
        }
        posts.add(post);
        return post;
    }

    public Post findOnePost(Integer userId, Integer postId) {
        User user = findOne(userId);

        if(user != null){
            return user.getPosts().stream()
                    .filter(post -> postId.equals(post.getId()))
                    .findAny()
                    .orElse(null);
        }

        return null;
    }

}
