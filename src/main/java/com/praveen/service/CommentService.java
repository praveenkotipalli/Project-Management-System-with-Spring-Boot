package com.praveen.service;

import com.praveen.model.Comment;
import java.util.*;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId) throws Exception;

}
