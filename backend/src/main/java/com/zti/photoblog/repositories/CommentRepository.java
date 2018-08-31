package com.zti.photoblog.repositories;

import com.zti.photoblog.models.Comment;
import com.zti.photoblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Comment repository
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Get all comments by id of the post
     *
     * @param id id of the post
     * @return list of comments
     */
    List<Comment> findByOnId(long id);
}
