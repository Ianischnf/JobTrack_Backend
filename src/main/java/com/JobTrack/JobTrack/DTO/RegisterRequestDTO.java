package com.JobTrack.JobTrack.DTO;

public record RegisterRequestDTO(
        String FirstName,
        String LastName,
        String Email,
        String Password,
        String ConfirmPassword
) {
}
