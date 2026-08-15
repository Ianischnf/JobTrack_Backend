package com.JobTrack.JobTrack.controller;

import com.JobTrack.JobTrack.DTO.CandidacyRequestDTO;
import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdateDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdatedDTO;
import com.JobTrack.JobTrack.service.CandidacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidacy")
public class CandidacyController {

    private final CandidacyService candidacyService;

    @PostMapping
    public CandidacyResponseDTO saveCandidacy(@Valid @RequestBody CandidacyRequestDTO candidacyRequestDTO){
        return this.candidacyService.saveCandidacy(candidacyRequestDTO);
    }

    @GetMapping
    public List<CandidacyResponseDTO> fetchAllCandidacies(){
        return this.candidacyService.fetchAllCandidacies();
    }

    @GetMapping("/{id}")
    public CandidacyResponseDTO fetchCandidacyById(@PathVariable("id") Long id){
        return this.candidacyService.fetchCandidacyById(id);
    }

    @PutMapping("/{id}")
    public CandidacyUpdatedDTO updateCandidacy(@Valid @RequestBody CandidacyUpdateDTO candidacyUpdateDTO, @PathVariable("id") Long id) {
        return this.candidacyService.updateCandidacy(candidacyUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidacy(@PathVariable("id") Long id){
        this.candidacyService.DeleteCandidacy(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCandidacy(){
        this.candidacyService.DeleteAllCandidacy();
    }
}
