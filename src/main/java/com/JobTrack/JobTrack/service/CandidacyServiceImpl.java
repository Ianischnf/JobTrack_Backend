package com.JobTrack.JobTrack.service;

import com.JobTrack.JobTrack.DTO.CandidacyRequestDTO;
import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdatedDTO;
import com.JobTrack.JobTrack.entity.Candidacy;
import com.JobTrack.JobTrack.enums.CandidacyStatus;
import com.JobTrack.JobTrack.repository.CandidacyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public class CandidacyServiceImpl implements CandidacyService{

    CandidacyRepository candidacyRepository;


    @Override
    public CandidacyResponseDTO saveCandidacy(CandidacyRequestDTO request) {
        Candidacy candidacy = new Candidacy();

        candidacy.setCompany(request.company());
        candidacy.setJobTitle(request.jobTitle());
        candidacy.setDateCandidacy(request.dateCandidacy());
        candidacy.setStatus(CandidacyStatus.ENVOYEE);

        Candidacy saved = candidacyRepository.save(candidacy);

        return new CandidacyResponseDTO(
                saved.getId(),
                saved.getCompany(),
                saved.getJobTitle(),
                saved.getDateCandidacy(),
                saved.getStatus()
        );
    }

    @Override
    public List<CandidacyResponseDTO> fetchAllCandidacies() {
        return List.of();
    }

    @Override
    public CandidacyUpdatedDTO updateCandidacy(CandidacyRequestDTO candidacyRequestDTO, Long id) {
        return null;
    }

    @Override
    public void DeleteCandidacy(Long id) {

    }
}
