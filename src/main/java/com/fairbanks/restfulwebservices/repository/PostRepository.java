package com.fairbanks.restfulwebservices.repository;

import com.fairbanks.restfulwebservices.model.Post;
import com.fairbanks.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
