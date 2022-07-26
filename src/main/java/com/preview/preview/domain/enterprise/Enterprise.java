package com.preview.preview.domain.enterprise;

import lombok.*;

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
