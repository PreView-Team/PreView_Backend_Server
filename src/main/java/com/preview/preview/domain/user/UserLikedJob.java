package com.preview.preview.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_liked_job")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLikedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_liked_job_id")
    private Long id;
}
