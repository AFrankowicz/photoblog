package com.zti.photoblog.controllers;

import com.zti.photoblog.models.Comment;
import com.zti.photoblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * Endpoints for comments
 */
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commenntService;

    /**
     * Endpoint for getting all coments to a post
     *
     * @param  postId  id of commented post
     * @return response
     */
    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.GET)
    public ResponseEntity getComments(@PathVariable long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commenntService.getCommentsByPostId(postId));
    }

    /**
     * Endpoint for adding comment to a post
     *
     * @param  postId  id of commented post
     * @param  comment  new comment
     * @return response
     */
    @RequestMapping(value = "/posts/{postId}/comment", method = RequestMethod.POST)
    public ResponseEntity publishComment(@PathVariable long postId, @RequestBody Comment comment) {
        return commenntService.publishComment(comment, postId);
    }

    /**
     * Endpoint for deleting comment
     *
     * @param  postId id of commented post
     * @param  id comment id
     * @return response
     */
    @RequestMapping(value = "/posts/{postId}/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable long postId, @PathVariable long id) {
        return commenntService.deleteComment(id);
    }
}
