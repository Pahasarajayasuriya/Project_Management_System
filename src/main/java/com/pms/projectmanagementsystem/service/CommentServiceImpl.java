package com.pms.projectmanagementsystem.service;

import com.pms.projectmanagementsystem.model.Comment;
import com.pms.projectmanagementsystem.model.Issue;
import com.pms.projectmanagementsystem.model.User;
import com.pms.projectmanagementsystem.repository.CommentRepository;
import com.pms.projectmanagementsystem.repository.IssueRepository;
import com.pms.projectmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new Exception("No user found with userId: "+userId);
        }
        if (issueOptional.isEmpty()){
            throw new Exception("No issue found with issueId: "+issueId);
        }

        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setUser(user);
        comment.setContent(content);
        comment.setCreatedDateTime(LocalDate.now());

        Comment savedComment = commentRepository.save(comment);
        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()){
            throw new Exception("No user found with userId: "+userId);
        }
        if (commentOptional.isEmpty()){
            throw new Exception("No comment found with commentId: "+commentId);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();

        if (comment.getUser().equals(user)) {
            commentRepository.delete(comment);
        } else {
            throw new Exception("User is not authorized to delete this comment");
        }

    }

    @Override
    public List<Comment> getCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
}
