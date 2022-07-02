package com.preview.preview.domain.likedEnterprise;

import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "liked_enterprise")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikedEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_enterprise_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    Enterprise enterprise;
}
