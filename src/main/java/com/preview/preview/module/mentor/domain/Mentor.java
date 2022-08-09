package com.preview.preview.module.mentor.domain;

import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.job.domain.MentorJob;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentor_id", nullable = false)
    private Long id;

    private String nickname; // 닉네임

    private String contents; // 소개

    @Setter
    @ManyToMany
    @JoinTable(
            name = "mentor_mentor_job",
            joinColumns = {@JoinColumn(name = "mentor_id", referencedColumnName = "mentor_id")},
            inverseJoinColumns = {@JoinColumn(name = "mentor_job_name", referencedColumnName = "mentor_job_name")}
    )
    private Set<MentorJob> likedJobs;


    public List<String> getMentorJobList(){
        Iterator<MentorJob> iterator = likedJobs.iterator();
        List<String> list = new ArrayList<>();

        while (iterator.hasNext()){
            MentorJob job = iterator.next();
            list.add(job.getName());
        }
        return list;
    }

    public String getNickname(){
        return nickname;
    }

    public String getContents(){
        return contents;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setContents(String contents){
        this.contents = contents;
    }
    public Long getId(){
        return id;
    }

}
