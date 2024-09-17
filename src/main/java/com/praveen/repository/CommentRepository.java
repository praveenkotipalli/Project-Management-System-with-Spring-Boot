package com.praveen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByIssueId(Long issueId);
    // List<Comment> findCommentByUser
}
