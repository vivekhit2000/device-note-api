package com.example.devicenote.service;

import com.example.devicenote.dto.NoteRequest;
import com.example.devicenote.dto.NoteResponse;
import com.example.devicenote.entity.DeviceNote;
import com.example.devicenote.exception.BadRequestException;
import com.example.devicenote.repository.DeviceNoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceNoteService {

    private static final Logger log = LoggerFactory.getLogger(DeviceNoteService.class);
    private final DeviceNoteRepository repo;
    private static final int MAX_LENGTH = 1000;

    public DeviceNoteService(DeviceNoteRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public NoteResponse createNote(Long deviceId, String username, NoteRequest req) {

        // Validate header
        if (username == null || username.isBlank()) {
            log.warn("X-User header missing for device {}", deviceId);
            throw new BadRequestException("X-User header required");
        }

        // Validate note
        if (req == null || req.getNote() == null || req.getNote().isBlank()) {
            log.warn("Empty note for device {} by {}", deviceId, username);
            throw new BadRequestException("Note must not be blank");
        }

        if (req.getNote().length() > MAX_LENGTH) {
            log.warn("Note too long for device {} by {}", deviceId, username);
            throw new BadRequestException("Note exceeds 1000 characters");
        }

        // Save note
        DeviceNote note = new DeviceNote();
        note.setDeviceId(deviceId);
        note.setNote(req.getNote());
        note.setCreatedBy(username);

        DeviceNote saved = repo.save(note);

        log.info("Note created for device {}", deviceId);

        // Map to response
        NoteResponse response = new NoteResponse();
        response.setId(saved.getId());
        response.setDeviceId(saved.getDeviceId());
        response.setNote(saved.getNote());
        response.setCreatedAt(saved.getCreatedAt());
        response.setCreatedBy(saved.getCreatedBy());

        return response;
    }

    public List<NoteResponse> listNotes(Long deviceId, Integer limit) {

        int effectiveLimit = (limit == null) ? 20 : limit;

        if (effectiveLimit < 1 || effectiveLimit > 100) {
            throw new BadRequestException("Limit must be between 1 and 100");
        }

        var notes = repo.findByDeviceIdOrderByCreatedAtDesc(
                deviceId,
                PageRequest.of(0, effectiveLimit)
        );

        // Map entities to response
        return notes.stream().map(note -> {
            NoteResponse res = new NoteResponse();
            res.setId(note.getId());
            res.setDeviceId(note.getDeviceId());
            res.setNote(note.getNote());
            res.setCreatedAt(note.getCreatedAt());
            res.setCreatedBy(note.getCreatedBy());
            return res;
        }).collect(Collectors.toList());
    }
}
