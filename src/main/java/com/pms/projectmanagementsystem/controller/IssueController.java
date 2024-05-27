package com.pms.projectmanagementsystem.controller;

import com.pms.projectmanagementsystem.model.Issue;
import com.pms.projectmanagementsystem.model.IssueDTO;
import com.pms.projectmanagementsystem.model.User;
import com.pms.projectmanagementsystem.repository.IssueRepository;
import com.pms.projectmanagementsystem.request.IssueRequest;
import com.pms.projectmanagementsystem.response.AuthResponse;
import com.pms.projectmanagementsystem.response.MessageResponse;
import com.pms.projectmanagementsystem.service.IssueService;
import com.pms.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssuesByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue, @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

                Issue createdIssue = issueService.createIssue(issue, tokenUser);
                IssueDTO issueDTO = new IssueDTO();
                issueDTO.setId(createdIssue.getId());
                issueDTO.setTitle(createdIssue.getTitle());
                issueDTO.setDescription(createdIssue.getDescription());
                issueDTO.setStatus(createdIssue.getStatus());
                issueDTO.setPriority(createdIssue.getPriority());
                issueDTO.setDueDate(createdIssue.getDueDate().toString());
                issueDTO.setProjectId(createdIssue.getProjectID());
                issueDTO.setProject(createdIssue.getProject());
                issueDTO.setTags(createdIssue.getTags());
                issueDTO.setAssignee(createdIssue.getAssignee());

        return ResponseEntity.ok(issueDTO);

    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization") String token)
            throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, tokenUser.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted successfully");

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId)
            throws Exception {
        return ResponseEntity.ok(issueService.addUserToIssue(issueId, userId));
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status,
                                                   @PathVariable Long issueId)
            throws Exception {
        return ResponseEntity.ok(issueService.updateStatus(issueId, status));
    }
}
