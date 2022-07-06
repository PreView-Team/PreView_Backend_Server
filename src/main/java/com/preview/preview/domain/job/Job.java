package com.preview.preview.domain.job;
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
    @Column(name = "job_name")
    private String name;
}
