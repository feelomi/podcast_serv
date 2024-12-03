package com.example.podcast_service.controller;

import com.example.podcast_service.dto.PodcastRequest;
import com.example.podcast_service.model.Podcast;
import com.example.podcast_service.service.PodcastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
@RequiredArgsConstructor
public class PodcastController {
    private final PodcastService podcastService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Podcast createPodcast(@RequestBody PodcastRequest request) {
        return podcastService.createPodcast(request);
    }
    
    @GetMapping
    public List<Podcast> getAllPodcasts() {
        return podcastService.getAllPodcasts();
    }
    
    @GetMapping("/author/{authorId}")
    public List<Podcast> getPodcastsByAuthor(@PathVariable Long authorId) {
        return podcastService.getPodcastsByAuthor(authorId);
    }
}