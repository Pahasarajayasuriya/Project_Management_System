package com.pms.projectmanagementsystem.controller;

import com.pms.projectmanagementsystem.model.Comment;
import com.pms.projectmanagementsystem.model.User;
import com.pms.projectmanagementsystem.request.CreateCommentRequest;
import com.pms.projectmanagementsystem.service.CommentService;
import com.pms.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createCommentRequest(@RequestBody CreateCommentRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(), user.getId(), req.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) {
        List<Comment> comments = commentService.getCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
