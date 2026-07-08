package com.JobTrack.JobTrack.controller;

import com.JobTrack.JobTrack.DTO.CandidacyRequestDTO;
import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdatedDTO;
import com.JobTrack.JobTrack.service.CandidacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/candidacy")
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

    @GetMapping CandidacyResponseDTO fetchCandidacyById(@PathVariable("id") Long id){
        return this.candidacyService.fetchCandidacyById(id);
    }

    @PutMapping("{/id}")
    public CandidacyUpdatedDTO updateCandidacy(@Valid @RequestBody CandidacyRequestDTO candidacyRequestDTO, @PathVariable("id") Long id) {
        return this.candidacyService.updateCandidacy(candidacyRequestDTO, id);
    }

    @DeleteMapping("{/id}")
    public void deleteCandidacy(@PathVariable("id") Long id){
        this.candidacyService.DeleteCandidacy(id);
    }
}
