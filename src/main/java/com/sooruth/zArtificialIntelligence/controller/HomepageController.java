package com.sooruth.zArtificialIntelligence.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class HomepageController {
    public static final LocalDateTime dateAppDeployed = LocalDateTime.now();

    @GetMapping
    public String displayMessage() {
        return "Application successfully deployed on: " + dateAppDeployed;
    }
}
