package com.JobTrack.JobTrack.service;

import com.JobTrack.JobTrack.DTO.UserRequestDTO;
import com.JobTrack.JobTrack.DTO.UserResponseDTO;
import com.JobTrack.JobTrack.entity.User;

public interface UserService {

    public UserResponseDTO FetchUserById(Long id);
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    public void deleteUser(Long id);
}
