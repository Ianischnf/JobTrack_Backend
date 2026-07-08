package com.JobTrack.JobTrack.service;

import com.JobTrack.JobTrack.DTO.CandidacyRequestDTO;
import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdateDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdatedDTO;

import java.util.List;

public interface CandidacyService {

    public CandidacyResponseDTO saveCandidacy (CandidacyRequestDTO candidacyRequestDTO);
    public List<CandidacyResponseDTO> fetchAllCandidacies();
    public CandidacyResponseDTO fetchCandidacyById(Long id);
    public CandidacyUpdatedDTO updateCandidacy(CandidacyUpdateDTO candidacyUpdateDTO, Long id);
    public void DeleteCandidacy(Long id);
}
