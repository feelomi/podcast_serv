package com.example.podcast_service.service;

import com.example.podcast_service.client.UserServiceClient;
import com.example.podcast_service.dto.PodcastRequest;
import com.example.podcast_service.model.Podcast;
import com.example.podcast_service.repository.PodcastRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PodcastService {
    private final PodcastRepository podcastRepository;
    private final UserServiceClient userServiceClient;
    
    public Podcast createPodcast(PodcastRequest request) {
        boolean userExists = userServiceClient.userExists(request.getAuthorId());
        if (!userExists) {
            throw new RuntimeException("Author not found");
        }
        
        Podcast podcast = Podcast.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .authorId(request.getAuthorId())
                .audioUrl(request.getAudioUrl())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
                
        return podcastRepository.save(podcast);
    }
    
    public List<Podcast> getAllPodcasts() {
        return podcastRepository.findAll();
    }
    
    public List<Podcast> getPodcastsByAuthor(Long authorId) {
        return podcastRepository.findByAuthorId(authorId);
    }
}