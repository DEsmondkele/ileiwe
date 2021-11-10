package com.ileiwe.data.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {

    private String title;
    @Column(length = 1000)
    private  String description;

    private  String language;

    private String imageUrls;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    private  LocalDateTime datePublished;

    private  LocalDateTime dateUpdated;


}
