package com.JobTrack.JobTrack.repository;

import com.JobTrack.JobTrack.DTO.CandidacyResponseDTO;
import com.JobTrack.JobTrack.entity.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidacyRepository extends JpaRepository<Candidacy, Long> {

}
