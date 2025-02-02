package com.vignatrics.Abdm.repo;

import com.vignatrics.Abdm.entity.Patientshare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientShareRepo extends JpaRepository<Patientshare,String> {
}
