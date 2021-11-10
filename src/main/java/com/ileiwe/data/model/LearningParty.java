package com.ileiwe.data.model;

import com.ileiwe.data.dto.StudentPartyDto;
import com.ileiwe.data.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearningParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,nullable = false)
    @NotBlank @NotNull
    private String email;


    @Column(nullable = false)
    @NotBlank @NotNull
    private  String passWord;

    private  boolean enabled;

    @CreationTimestamp
    private LocalDateTime dateCreated;


    private String token;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty( String email, String passWord, Authority authority){
        this.email = email;
        this.passWord = passWord;
        addAuthority(authority);
        this.enabled = false;

    }


    public void addAuthority(Authority authority){
        if(this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(authority);
    }
}
