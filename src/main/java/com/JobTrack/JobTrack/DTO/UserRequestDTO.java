package com.JobTrack.JobTrack.DTO;

public record UserRequestDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
