package com.gs.CareerBooster.controller;

import com.gs.CareerBooster.dto.CreateTrackDto;
import com.gs.CareerBooster.dto.UpdateTrackDto;
import com.gs.CareerBooster.dto.TrackResponseDto;
import com.gs.CareerBooster.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping
    public ResponseEntity<TrackResponseDto> createTrack(@Valid @RequestBody CreateTrackDto dto) {
        TrackResponseDto response = trackService.createTrack(dto);
        return ResponseEntity.created(URI.create("/tracks/" + response.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponseDto> getTrackById(@PathVariable Integer id) {
        return ResponseEntity.ok(trackService.getTrackById(id));
    }

    @GetMapping
    public ResponseEntity<List<TrackResponseDto>> getAllTracks() {
        return ResponseEntity.ok(trackService.getAllTracks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackResponseDto> updateTrack(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateTrackDto dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(trackService.updateTrack(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrack(@PathVariable Integer id) {
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }
}
