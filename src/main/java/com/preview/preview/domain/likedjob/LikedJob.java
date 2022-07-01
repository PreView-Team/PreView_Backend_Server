package com.preview.preview.domain.likedjob;


import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "liked_job")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_job_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    Job job;

}
