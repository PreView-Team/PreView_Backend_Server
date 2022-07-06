package com.preview.preview.domain.enterprise;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "enterprise")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    @Id
    @Column(name = "enterprise_name")
    private String name;
}
