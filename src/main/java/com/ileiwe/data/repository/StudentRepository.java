package com.ileiwe.data.repository;

import com.ileiwe.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {





}
