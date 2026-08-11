package com.JobTrack.JobTrack.controller;

import com.JobTrack.JobTrack.DTO.UserRequestDTO;
import com.JobTrack.JobTrack.DTO.UserResponseDTO;
import com.JobTrack.JobTrack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")

public class UserController {
    private final UserService userService;

    @PostMapping("/{id}")
    public UserResponseDTO FetchUserById(@PathVariable("id") Long id){
        return this.userService.FetchUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable("id") Long id){
        return this.userService.updateUser(id, userRequestDTO);
    }

    @PutMapping("/me")
    public UserResponseDTO fetchCurrentUser(){
        return this.userService.fetchCurrentUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
    }
}
