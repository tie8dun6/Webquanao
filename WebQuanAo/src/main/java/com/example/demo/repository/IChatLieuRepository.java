package com.example.demo.repository;

import com.example.demo.model.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChatLieuRepository extends JpaRepository<ChatLieu,Integer> {
    ChatLieu findChatLieuById(int idHangValue);

    ChatLieu findChatLieuByTen(String tenChatLieu);
}
