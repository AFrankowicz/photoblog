package com.zti.photoblog.services;

import com.zti.photoblog.aspects.LogExecution;
import com.zti.photoblog.config.AccountUserDetails;
import com.zti.photoblog.models.Post;
import com.zti.photoblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Post service
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Get all posts
     *
     * @return list of posts
     */
    @LogExecution
    @Transactional
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }


    /**
     * Get posts by account username
     *
     * @param username
     * @return list of posts
     */
    @LogExecution
    @Transactional
    public List<Post> getPostsByAccount(String username) {
       return postRepository.findByPublishedById(accountService.getAccountByUsername(username).getId());
    }

    /**
     * Add new post
     *
     * @param post new post
     * @return response
     */
    @LogExecution
    @Transactional
    public ResponseEntity publishPost(Post post){
        AccountUserDetails userDetails = (AccountUserDetails)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        post.setPublishedBy(accountService.getAccountByUsername(userDetails.getUsername()));
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    public String publishPostDefault(Post post){
        postRepository.save(post);
        return "Published";
    }

    /**
     * Get post details by id
     *
     * @param id id of a post
     * @return post
     */
    @LogExecution
    @Transactional
    public Post getPost(long id){
        return postRepository.getOne(id);
    }
}
