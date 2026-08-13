package com.JobTrack.JobTrack.DTO;

public record UserRequestDTO(
        Long id,
        String lastName,
        String firstName,
        String email
) {
}
