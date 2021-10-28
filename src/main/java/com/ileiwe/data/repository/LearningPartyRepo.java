package com.ileiwe.data.repository;

import com.ileiwe.data.model.LearningParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPartyRepo extends JpaRepository<LearningParty, Long> {
}
