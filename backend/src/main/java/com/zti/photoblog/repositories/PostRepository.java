package com.zti.photoblog.repositories;

import com.zti.photoblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Post repository
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Get posts by account id
     *
     * @param id of account
     * @return list of posts
     */
    List<Post> findByPublishedById(Long id);
}
