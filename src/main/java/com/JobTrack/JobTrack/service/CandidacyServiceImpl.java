package com.JobTrack.JobTrack.service;

import com.JobTrack.JobTrack.DTO.CandidacyRequestDTO;
import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdateDTO;
import com.JobTrack.JobTrack.DTO.CandidacyUpdatedDTO;
import com.JobTrack.JobTrack.entity.Candidacy;
import com.JobTrack.JobTrack.enums.CandidacyStatus;
import com.JobTrack.JobTrack.repository.CandidacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CandidacyServiceImpl implements CandidacyService{

    private final CandidacyRepository candidacyRepository;


    @Override
    public CandidacyResponseDTO saveCandidacy(CandidacyRequestDTO request) {
        Candidacy candidacy = new Candidacy();

        candidacy.setCompany(request.company());
        candidacy.setJobTitle(request.jobTitle());
        candidacy.setDateCandidacy(request.dateCandidacy());

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

        return candidacyRepository.findAll()
                .stream()
                .map(candidacy -> new CandidacyResponseDTO(
                        candidacy.getId(),
                        candidacy.getCompany(),
                        candidacy.getJobTitle(),
                        candidacy.getDateCandidacy(),
                        candidacy.getStatus()
                )).toList();
       /* List<Candidacy> candidacies = candidacyRepository.findAll();
        List<CandidacyResponseDTO> dtos = new ArrayList<>();

        for(Candidacy c : candidacies) {
            dtos.add(new CandidacyResponseDTO(
                    c.getId(),
                    c.getCompany(),
                    c.getJobTitle(),
                    c.getDateCandidacy(),
                    c.getStatus()
            ));
        }
        return dtos;
        */
    }

    @Override
    public CandidacyResponseDTO fetchCandidacyById(Long id) {
        Candidacy candidacy = candidacyRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidature indivuelle introuvable"));

        return new CandidacyResponseDTO(
                candidacy.getId(),
                candidacy.getCompany(),
                candidacy.getJobTitle(),
                candidacy.getDateCandidacy(),
                candidacy.getStatus()
        );
    }

    @Override
    public CandidacyUpdatedDTO updateCandidacy(CandidacyUpdateDTO candidacyUpdateDTO, Long id) {
        Candidacy candidacy = candidacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));

       candidacy.setCompany(candidacyUpdateDTO.company());
       candidacy.setJobTitle(candidacyUpdateDTO.jobTitle());
       candidacy.setDateCandidacy(candidacyUpdateDTO.dateCandidacy());
       candidacy.setStatus(candidacyUpdateDTO.status());

       Candidacy updated =  candidacyRepository.save(candidacy);

       return new CandidacyUpdatedDTO(
               updated.getId(),
               updated.getCompany(),
               updated.getJobTitle(),
               updated.getDateCandidacy(),
               updated.getStatus()
       );
    }

    @Override
    public void DeleteCandidacy(Long id) {
        candidacyRepository.deleteById(id);
    }
}
