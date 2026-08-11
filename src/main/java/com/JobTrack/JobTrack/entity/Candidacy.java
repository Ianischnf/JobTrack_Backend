package com.JobTrack.JobTrack.entity;


import com.JobTrack.JobTrack.enums.CandidacyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "candidacy")
@Data

public class Candidacy {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String company;
    private String jobTitle;
    private LocalDate dateCandidacy;
    private String webSite;

    @Enumerated(EnumType.STRING)
    private CandidacyStatus  status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
