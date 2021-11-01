package com.ileiwe.data.repository;

import com.ileiwe.data.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface LearningPartyRepository extends JpaRepository<LearningParty, Long> {

LearningParty findByEmail(String Email);

@Query("select '*' from LearningParty " + "as L where L.email = :email")
LearningParty findUserByEmail(String email);

}
