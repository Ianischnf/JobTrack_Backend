package com.JobTrack.JobTrack.DTO;

import com.JobTrack.JobTrack.enums.CandidacyStatus;

import java.time.LocalDate;

public record CandidacyResponseDTO(
        Long id,
        String company,
        String jobTitle,
        LocalDate dateCandidacy,
        CandidacyStatus status
) {
}
