package com.pms.projectmanagementsystem.repository;

import com.pms.projectmanagementsystem.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{
    Invitation findByToken(String token);
    Invitation findByEmail(String userEmail);
}
