package com.pms.projectmanagementsystem.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteRequest {
    private String email;
    private Long projectId;

    @Override
    public String toString() {
        return "InviteRequest{" +
                "email='" + email + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
