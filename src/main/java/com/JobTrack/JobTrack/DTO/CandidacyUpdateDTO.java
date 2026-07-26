package com.JobTrack.JobTrack.DTO;

import com.JobTrack.JobTrack.enums.CandidacyStatus;

import java.time.LocalDate;

public record CandidacyUpdateDTO(
        String company,
        String jobTitle,
        LocalDate dateCandidacy,
        String webSite,
        CandidacyStatus status
) {
}
