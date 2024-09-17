package com.praveen.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequest {

    private Long senderId;

    // private Long chatId;

    private String content;

    private Long projectId;

}