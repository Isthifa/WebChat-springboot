package com.example.chatapplication.Util;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private MessageType type;
    private UUID userId;
    private String receiverName;
    private String senderName;
    private String date;
    private String status;
}
