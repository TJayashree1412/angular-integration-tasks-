package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository extends MongoRepository<Track, String>
{
    Optional<Track> findByTrackName(String trackName);
    boolean existsByTrackName(String trackName);

//    @Query("db.tracks.find( { trackName: { $regex: /?0/ } } )")
    @Query("{ trackName: { $regex: '?0', $options: 'i'} }")
    List<Track> searchTracksByName(String trackName);
}
