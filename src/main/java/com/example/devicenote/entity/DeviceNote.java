package com.example.devicenote.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "device_note")
public class DeviceNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID of the device note belongs to
    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    // Actual note text
    @Column(nullable = false, length = 1000)
    private String note;

    // Timestamp when the note was created (set by DB)
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    // User who created the note
    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
