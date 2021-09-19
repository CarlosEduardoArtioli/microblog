package com.dynaccurate.microblog.repository;

import com.dynaccurate.microblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
