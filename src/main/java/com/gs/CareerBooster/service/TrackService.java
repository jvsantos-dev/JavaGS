package com.gs.CareerBooster.service;

import com.gs.CareerBooster.dto.CreateTrackDto;
import com.gs.CareerBooster.dto.UpdateTrackDto;
import com.gs.CareerBooster.dto.TrackResponseDto;
import com.gs.CareerBooster.model.TrackModel;
import com.gs.CareerBooster.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public TrackResponseDto createTrack(CreateTrackDto dto) {
        TrackModel track = new TrackModel();
        track.setTitle(dto.getTitle());
        track.setDescription(dto.getDescription());
        track.setArea(dto.getArea());

        track = trackRepository.save(track);
        return toResponse(track);
    }

    public TrackResponseDto getTrackById(Integer id) {
        TrackModel track = trackRepository.findById(id);
        if (track == null) throw new RuntimeException("Trilha não encontrada");
        return toResponse(track);
    }

    public List<TrackResponseDto> getAllTracks() {
        return trackRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TrackResponseDto updateTrack(UpdateTrackDto dto) {
        TrackModel track = new TrackModel();
        track.setId(dto.getId());
        track.setTitle(dto.getTitle());
        track.setDescription(dto.getDescription());
        track.setArea(dto.getArea());

        boolean updated = trackRepository.update(track);
        if (!updated) throw new RuntimeException("Falha ao atualizar a trilha");

        return toResponse(track);
    }

    public void deleteTrack(Integer id) {
        boolean deleted = trackRepository.delete(id);
        if (!deleted) throw new RuntimeException("Trilha não encontrada");
    }

    private TrackResponseDto toResponse(TrackModel track) {
        TrackResponseDto dto = new TrackResponseDto();
        dto.setId(track.getId());
        dto.setTitle(track.getTitle());
        dto.setDescription(track.getDescription());
        dto.setArea(track.getArea());
        return dto;
    }
}
