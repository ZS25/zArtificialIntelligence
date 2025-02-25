package com.sooruth.zArtificialIntelligence.controller;


import com.sooruth.zArtificialIntelligence.record.BookRecord;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("respond with uppercase")
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }

    @GetMapping("/fact")
    public String fact() {
        return chatClient.prompt()
                .user("Tell me a random fact.")
                .call()
                .content();
    }

    @GetMapping("/book")
    public BookRecord book(@RequestParam String topic) {
        return chatClient.prompt()
                .user(u -> u.text("Give me a book recommendation on {topic}.  Limit the summary to 100 words.").param("topic", topic))
                .call()
                .entity(BookRecord.class);
    }

    @GetMapping("/books")
    public List<BookRecord> books(@RequestParam String topic) {
        return chatClient.prompt()
                .user(u -> u.text("Give me two books recommendation on {topic}.  Limit the summary to 100 words.").param("topic", topic))
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/chatstream")
    public Flux<String> chatWithStream(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

}