package com.JobTrack.JobTrack.service;

import com.JobTrack.JobTrack.DTO.UserRequestDTO;
import com.JobTrack.JobTrack.DTO.UserResponseDTO;
import com.JobTrack.JobTrack.entity.User;

public interface UserService {

    public UserResponseDTO FetchUserById(Long id);
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO);
    public UserResponseDTO fetchCurrentUser();
    public void deleteUser(Long id);
}
