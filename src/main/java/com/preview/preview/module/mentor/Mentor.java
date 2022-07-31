package com.preview.preview.module.mentor;

import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.job.domain.MentorJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id", nullable = false)
    private Long id;

    private String nickname; // 닉네임

    @Setter
    @ManyToMany
    @JoinTable(
            name = "mentor_mentor_job",
            joinColumns = {@JoinColumn(name = "mentor_id", referencedColumnName = "mentor_id")},
            inverseJoinColumns = {@JoinColumn(name = "mentor_job_name", referencedColumnName = "mentor_job_name")}
    )
    private Set<MentorJob> likedJobs;

    private String contents; // 소개
}
