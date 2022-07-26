package com.preview.preview.domain.email;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "email")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private long id;

    @Column(name = "email_name")
    private String name;

    @Column(name = "code")
    private String code;

}
