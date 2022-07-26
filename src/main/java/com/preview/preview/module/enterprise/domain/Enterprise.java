package com.preview.preview.module.enterprise.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    @Id
    @Column(name = "enterprise_name")
    private String name;
}
