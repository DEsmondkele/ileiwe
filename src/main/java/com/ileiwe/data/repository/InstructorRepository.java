package com.ileiwe.data.repository;

import com.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.data.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
public interface InstructorRepository extends JpaRepository <Instructor, Long> {

    Optional<Instructor> findById(InstructorPartyDto instructorPartyDto);

}
