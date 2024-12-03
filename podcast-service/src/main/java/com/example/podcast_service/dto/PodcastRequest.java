package com.example.podcast_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PodcastRequest {
    private String title;
    private String description;
    private Long authorId;
    private String audioUrl;
}