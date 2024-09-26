package Graduation.work.YongduriMarketServer.chat.entity;

import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.file.entity.FileInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // 보내는 사람의 프로필 객체

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient; // 받는 사람의 프로필 객체

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_info_id", nullable = true)
    private FileInfo fileInfo; // 받는 사람의 프로필 객체

    @Column(columnDefinition = "TEXT", nullable = true)
    private String message; //본문

    @Column(updatable = false)
    private LocalDateTime timestamp; // 타임스탬프

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;
}