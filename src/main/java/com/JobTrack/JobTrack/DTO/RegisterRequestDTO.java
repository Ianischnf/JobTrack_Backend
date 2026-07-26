package com.JobTrack.JobTrack.DTO;

public record RegisterRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String confirmPassword
) {
}
