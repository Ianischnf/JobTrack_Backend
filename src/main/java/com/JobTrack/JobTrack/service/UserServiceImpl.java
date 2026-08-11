package com.JobTrack.JobTrack.service;


import com.JobTrack.JobTrack.DTO.UserRequestDTO;
import com.JobTrack.JobTrack.DTO.UserResponseDTO;
import com.JobTrack.JobTrack.entity.User;
import com.JobTrack.JobTrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO FetchUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        return new UserResponseDTO(
           user.getId(),
           user.getFirstName(),
           user.getLastName(),
           user.getEmail()
        );
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setLastName(userRequestDTO.lastName());
        user.setFirstName(user.getFirstName());
        user.setEmail(userRequestDTO.email());

        return new UserResponseDTO(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail()
        );
    }

    @Override
    public UserResponseDTO fetchCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));


        return new UserResponseDTO(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail()
        );
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
