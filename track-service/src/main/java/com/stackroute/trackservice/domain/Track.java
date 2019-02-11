package com.stackroute.trackservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tracks")
@Data

public class Track
{
    @Id
    private String trackId;
    private String trackName;
    private String trackUrl;
    private String trackSinger;
    private String trackImageUrl;
    private String comment;
}
