package com.JobTrack.JobTrack.DTO;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
