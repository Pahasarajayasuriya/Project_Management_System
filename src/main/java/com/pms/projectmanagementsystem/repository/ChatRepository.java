package com.pms.projectmanagementsystem.repository;

import com.pms.projectmanagementsystem.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<Chat,Long> {
}
