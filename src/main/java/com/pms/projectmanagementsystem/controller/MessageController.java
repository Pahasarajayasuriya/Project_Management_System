package com.pms.projectmanagementsystem.controller;

import com.pms.projectmanagementsystem.model.Chat;
import com.pms.projectmanagementsystem.model.Message;
import com.pms.projectmanagementsystem.model.User;
import com.pms.projectmanagementsystem.request.CreateMessageRequest;
import com.pms.projectmanagementsystem.service.MessageService;
import com.pms.projectmanagementsystem.service.ProjectService;
import com.pms.projectmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {
        User user = userService.findUserById(request.getSenderId());
        Chat chats = projectService.getChatByProjectId((request.getProjectId()));
        if(chats==null){
            throw new Exception("No chat found");
        }
        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<?> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
