package com.example.devicenote.controller;

import com.example.devicenote.dto.NoteRequest;
import com.example.devicenote.dto.NoteResponse;
import com.example.devicenote.service.DeviceNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoints for managing device notes.
 */
@RestController
@RequestMapping("/api/v1/devices/{deviceId}/notes")
public class DeviceNoteController {

    private static final Logger log = LoggerFactory.getLogger(DeviceNoteController.class);
    private final DeviceNoteService service;

    public DeviceNoteController(DeviceNoteService service) {
        this.service = service;
    }

    /**
     * Create a note for a device.
     */
    @PostMapping
    public NoteResponse createNote(
            @PathVariable Long deviceId,
            @RequestHeader(value = "X-User", required = false) String username,
            @RequestBody NoteRequest request) {

        log.info("Create note request → deviceId={}, user={}", deviceId, username);
        return service.createNote(deviceId, username, request);
    }

    /**
     * Fetch notes for a device with optional limit.
     */
    @GetMapping
    public List<NoteResponse> listNotes(
            @PathVariable Long deviceId,
            @RequestParam(value = "limit", required = false) Integer limit) {

        log.info("Fetch notes → deviceId={}, limit={}", deviceId, limit);
        return service.listNotes(deviceId, limit);
    }
}
