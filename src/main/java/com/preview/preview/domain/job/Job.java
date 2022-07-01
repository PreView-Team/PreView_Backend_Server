package com.preview.preview.domain.job;

import com.preview.preview.domain.likedjob.LikedJob;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "job")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "job")
    Set<LikedJob> likedJobs;
}
