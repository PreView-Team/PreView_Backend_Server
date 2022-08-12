package com.preview.preview.module.job.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mentor_job")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorJob {
    @Id
    @Column(name = "mentor_job_name")
    private String name;
}
