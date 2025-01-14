package com.sooruth.zArtificialIntelligence.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/fact")
    public String fact() {
        return chatClient.prompt()
                .user("Tell me a random fact.")
                .call()
                .content();
    }

}