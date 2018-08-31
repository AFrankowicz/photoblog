package com.zti.photoblog.controllers;

import com.zti.photoblog.models.Post;
import com.zti.photoblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Endpoints for posts
 */
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Endpoint for getting all posts
     *
     * @return response
     */
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    /**
     * Endpoint for getting all posts for given account
     *
     * @param  username username of account
     * @return response
     */
    @RequestMapping(value = "/accounts/{username}/posts")
    public ResponseEntity getPostForUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByAccount(username));
    }

    /**
     * Endpoint for publishing new post
     *
     * @param  post new post
     * @return response
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity publishPost(@RequestBody Post post){
        return postService.publishPost(post);
    }

    /**
     * Endpoint for getting specific post
     *
     * @param  id post id
     * @return response
     */
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
    }
}
