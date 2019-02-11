package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exception.EmptyTrackListException;
import com.stackroute.trackservice.exception.TrackAlreadyExistsException;
import com.stackroute.trackservice.exception.TrackDoesNotExistException;
import com.stackroute.trackservice.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Api(description="Track Service")
public class TrackController
{
    private TrackService trackService;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService = trackService;
    }

    @ApiOperation(value = "Save Track")
    @PostMapping("/tracks/track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)
    {
        try
        {
            return new ResponseEntity<Track>(trackService.saveTrack(track), HttpStatus.OK);
        }
        catch (TrackAlreadyExistsException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update Track")
    @PutMapping("/tracks/track")
    public ResponseEntity<?> updateTrack(@RequestBody Track updatedTrack)
    {
        try
        {
            trackService.updateTrack(updatedTrack);
            return new ResponseEntity<Track>(updatedTrack, HttpStatus.OK);
        }
        catch (TrackDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get Track By TrackID")
    @GetMapping("/tracks/track/id/{trackId}")
    public ResponseEntity<?> getTrackById(@PathVariable String trackId)
    {
        try
        {
            return new ResponseEntity<Track>(trackService.getTrackById(trackId), HttpStatus.OK);
        }
        catch (TrackDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get Track By TrackName")
    @GetMapping("/tracks/track/name/{trackName}")
    public ResponseEntity<?> getTrackByName(@PathVariable String trackName)
    {
        try
        {
            return new ResponseEntity<Track>(trackService.getTrackByName(trackName), HttpStatus.OK);
        }
        catch (TrackDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Delete Track By TrackID")
    @DeleteMapping("/tracks/track/{trackId}")
    public ResponseEntity<?> deleteTrackById(@PathVariable String trackId)
    {
        try
        {
            return new ResponseEntity<Track>(trackService.deleteTrack(trackService.getTrackById(trackId)), HttpStatus.OK);
        }
        catch (TrackDoesNotExistException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get List Of All Tracks")
    @GetMapping("/tracks")
    public ResponseEntity<?>getAllTracks()
    {
        try
        {
            return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        }
        catch (EmptyTrackListException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Search Tracks")
    @GetMapping("/tracks/{trackName}")
    public ResponseEntity<?>searchTracks(@PathVariable String trackName)
    {
        try
        {
            return new ResponseEntity<List<Track>>(trackService.searchTracks(trackName), HttpStatus.OK);
        }
        catch (EmptyTrackListException e)
        {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
