package com.zti.photoblog.services;

import com.zti.photoblog.aspects.LogExecution;
import com.zti.photoblog.config.AccountUserDetails;
import com.zti.photoblog.models.Comment;
import com.zti.photoblog.models.Post;
import com.zti.photoblog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Comments service
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PostService postService;

    /**
     * Get comments by post id
     *
     * @param postId
     * @return list of comments
     */
    @LogExecution
    @Transactional
    public List<Comment> getCommentsByPostId (long postId){
        return commentRepository.findByOnId(postId);
    }

    /**
     * Publish new comment
     *
     * @param comment new comment
     * @params postId id of commented post
     * @return response entity
     */
    @LogExecution
    @Transactional
    public ResponseEntity publishComment(Comment comment, long postId){
        Post post = postService.getPost(postId);
        comment.setOn(post);
        AccountUserDetails userDetails = (AccountUserDetails)SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        comment.setPublishedBy(accountService.getAccountByUsername(userDetails.getUsername()));
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    /**
     * Delete a comment
     *
     * @param id id of a comment
     * @return response
     */
    @LogExecution
    @Transactional
    public ResponseEntity deleteComment(long id){
        commentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
